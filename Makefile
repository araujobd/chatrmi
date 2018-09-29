SOURCES = `find . -name "*.kt"`
DIR = build
OUT = out
CLIENT = ChatClientKt
SERVER = ChatServerKt

all: clean
	-mkdir build
	kotlinc $(SOURCES) -include-runtime -d $(DIR)/$(OUT).jar

clean:
	-rm -rf $(DIR)

server:
	kotlin -classpath $(DIR)/$(OUT).jar $(SERVER) ${ARGS}

client:
	kotlin -classpath $(DIR)/$(OUT).jar $(CLIENT) ${ARGS}
