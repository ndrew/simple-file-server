# file-server


Running will serve static files from example directory. Calling localhost:{port}/get-xml-files will return list of all xml files in json format.

    java -jar server.jar --dir example/

Or just copy server.jar to directory you want to use as source of xml files(e.g. example/) and do

    java -jar example/server.jar

Enjoy.


# Compilation / modification

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

    or 

    lein run {flags}

## License

Copyright © 2013 FIXME
