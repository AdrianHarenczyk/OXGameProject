!#/bin/bash

set -e

mvn clean install
java -jar target/ox-game-project-1.0.jar