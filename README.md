## localhost:chat

##### A simple and sneaky chat server written in java to host and chat with your co-workers without any or trace!

Current features:
* No Database connectivity or logger!
* In memory storage (currently limited to 10 messages)
* Json format for displaying data on screen
* Text based command to edit, delete. clear messages

Future updates:
* Replies to specific text (Work in progress)
* Xml format
* Ability to switch between json,xml and normal message format
* Better in-memory storage and scrollable sceeen  


#### Uses

Deploy it on `tomcat 9.0`, using `eclipse`. run the server and goto `localhost:8080/localhostchat/`

#### Commands

`enter key` is send.  
`/edit typo new_text` can be use to edit your last text. (Bug: doesn't works for numbers)  
`/last` to remove your last message.  
`/last n` to remove your last n messages.  
`/clear` or `/cls` or `/clean` to clear all the messages.  
`/testing` or `/dummy` to generate dummy messages.  