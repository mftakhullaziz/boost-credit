SHELL=/bin/bash
HOME := $(shell pwd)

# RUNNING CLEAN TEST
cleanTest/bookService:
	./gradlew clean test

# RUNNING CLEAN BUILD
clean-build/bookService:
	./gradlew clean build

# RUNNING BUILD
build/bookService:
	./gradlew build

# RUNNING SERVICE
run/bookService:
	java -jar build/libs/bookstore-0.0.1-SNAPSHOT.jar

# RUNNING BUILD AND RUN SERVICE
service/bookService: clean-build/bookService run/bookService