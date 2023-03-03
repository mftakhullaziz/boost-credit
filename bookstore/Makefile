SHELL=/bin/bash
HOME := $(shell pwd)

cleanTest/bookService:
	./gradlew clean test

clean-build/bookService:
	./gradlew clean build

build/bookService:
	./gradlew build

run/bookService:
	java -jar build/libs/bookstore-0.0.1-SNAPSHOT.jar

service/bookService:
	clean-build/bookService run/bookService