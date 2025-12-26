package word_counter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountWordTask extends RecursiveTask<Long> {
    private final Path path;
    private final String word;

    public CountWordTask(Path path, String word) {
        this.path = path;
        this.word = word;
    }

    @Override
    protected Long compute() {
        if (!Files.isDirectory(path)) {
            return WordCounter.countWords(path, word);
        }
        Stream<Path> subpaths;
        try {
            subpaths = Files.list(path);
        } catch (IOException e) {
            return 0L;
        }
        List<CountWordTask> subTasks =
                subpaths.map(path -> new CountWordTask(path, word))
                        .collect(Collectors.toList());
        invokeAll(subTasks);
        return subTasks
                .stream()
                .mapToLong(CountWordTask::getRawResult)
                .sum();
    }
}
