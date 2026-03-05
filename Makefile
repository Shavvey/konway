MAIN=src/main/*.kt
EXEC=konway

all: make run

make: $(MAIN)
	kotlinc $(MAIN) -include-runtime -d out/$(EXEC).jar

run: out/$(EXEC).jar
	java -jar out/$(EXEC).jar
