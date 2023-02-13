run-dev:
	./mvnw clean
	./mvnw install
	java -jar ./target/exam-sheet-system*.jar
