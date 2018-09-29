SOURCES = `find src/ -name "*.kt"`
DIR = build
OUT = chat
CLIENT = ChatClientKt
SERVER = ChatServerKt
RUN = java

all: clean
	-mkdir -p $(DIR)
	kotlinc $(SOURCES) -include-runtime -d $(DIR)/$(OUT).jar

clean:
	-rm -rf $(DIR)

server:
	$(RUN) -classpath $(DIR)/$(OUT).jar $(SERVER) ${ARGS}

client:
	$(RUN) -classpath $(DIR)/$(OUT).jar $(CLIENT) ${ARGS}
