MAIN=src/main.kt
EXEC=konway

all: src/main.kt
	kotlinc $(MAIN) -include-runtime -d out/$(EXEC).jar

run: out/$(EXEC).jar
	java -jar out/$(EXEC).jar
