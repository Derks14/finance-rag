# AI-Powered Financial Advisor
A simple application showing retrieval augment generation implementation using Spring AI and OPENAI's GPT models. This application enables inteliigent document querying by leveraging on LLMs and local document for context.

This project showcases how to:


Load PDF documents into a vector database



Execute semantic searches using Spring AI



Enhance LLM responses with pertinent document information



Develop an API endpoint for chat interactions informed by documents
## Overview

This project showcases how to:
- Load PDF documents into a vector database
- Execute semantic searches using Spring AI
- Enhance LLM responses with pertinent document information
- Develop an API endpoint for chat interactions informed by documents

## Project Requirements
- Java 17
- Maven
- Docker
- OpenAI API key

## Dependencies
- Spring Web
- Spring OpenAI
- Spring AI Document Reader
- Postgres Vector Database

## Getting Started
1. Configure your environment variables
```bash
export OPENAI_API_KEYS = putyourapikeyshere
```
2. Update application.properties
```properties
spring.ai.openai.api-key=${OPENAI_API_KEYS}
spring.ai.openai.chat.options.model=gpt-4o
spring.ai.vectorstore.pgvector.initialize-schema=true
```
3. Place pdf files in the `/src/main/resources/docs` folder

## Running the application
1. start docker
2. Launch the application
```bash
./mvnw spring-boot:run
```

This command will 
- Start a PostgreSQL database with PGVector extension
- Initialise the vector store schema
- Ingest documents from the configured location
- start a Tomcat Webserver on port 8080

## Making Requests
Send a get request using the url below
```bash
http://localhost:8080
```

## Architecture Highlights
- Document Processing : Spring AIs PDF document reader to parse documents into manageable chunks
- Vector Storage: Utilises PGVector for efficient similarity searches
- Context Retrieval: Automatic retrieves relevant document segments based on user queries
- Response Generation: Combines document contexr with GPT-4s capabilities for informed responses


