## What is this?
This is a proof of concept that demostrates a Java process that fetches keys specified by a configuration file using a managed identity (or a principal for dev mode) and inserts them into the current shell. The idea is the docker entrypoint calls this tool and any secrets required by the main of the service can pick them up from the env. Enjoy.

## Running
```
$ mvn package
#<several moments and rheems of scroll later>
$ cd target/
$ set -a
$ eval "$(java -jar keyvault-adapter-1-jar-with-dependencies.jar)"
$ set +a
$ env
...
FOO=foosecret
BAR=barsecret
...
```
