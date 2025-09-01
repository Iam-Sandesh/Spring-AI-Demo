# Spring-AI-Demo-
A hands-on demo project showcasing benefits of Spring AI to integrate advantages of AI into Spring Boot Application.

Spring AI With Ollama
- By default Spring AI will check for Mistral model if we are using ollama.
- If we want another model to run with Spring AI, configuration needs to be done in application.properties
- Spring boot application will talk to LLMs with ChatModel in between
- ChatClient can be used for more control over the response by LLMs like we can get the MetaData
- LLMs don't remember the chat history, Here we can use Memory Advisor, Before sending the request to the LLM, they attach relevant history so the AI "remembers."