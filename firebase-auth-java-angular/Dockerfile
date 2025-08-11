FROM openjdk:21-jdk-slim

# Install Node.js (version 22) and curl
RUN apt-get update && apt-get install -y curl \
  && curl -sL https://deb.nodesource.com/setup_22.x | bash - \
  && apt-get install -y nodejs \
  && npm install -g firebase-tools \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*


WORKDIR /workspace

CMD ["firebase", "emulators:start", "--only", "auth", "--project", "firebase-auth-java-angular"]
