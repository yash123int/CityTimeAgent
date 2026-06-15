# Hello Time Agent

A simple AI agent built with Google's Agent Development Kit (ADK) for Java. The agent tells the current time in a specified city using the Gemini language model and a custom function tool.

## Prerequisites

- Java 17 or later
- Maven 3.9 or later
- Google API Key ([Get one here](https://aistudio.google.com/app/apikey))

## Project Structure

```
my_agent/
    src/main/java/com/example/agent/
                        HelloTimeAgent.java   # Main agent code with time tool
                        AgentCliRunner.java   # Command-line interface
    pom.xml                                   # Project configuration
    .env                                      # API keys (not committed to git)
```

## Setup

1. Clone the repository:

```bash
git clone <your-repo-url>
cd my_agent
```

2. Set your Google API key:

**Windows (Command Prompt):**

```bash
set GOOGLE_API_KEY=your_api_key_here
```

**Mac / Linux:**

```bash
export GOOGLE_API_KEY=your_api_key_here
```

## Running the Agent

### CLI Mode

Run the agent in your terminal for a simple text-based conversation:

```bash
mvn compile exec:java -Dexec.mainClass="com.example.agent.AgentCliRunner"
```

Type your query (e.g., "What time is it in Mumbai?") and type `quit` to exit.

### Web UI Mode

Run the agent with a browser-based chat interface:

**Windows:**

```bash
mvn compile exec:java -Dexec.mainClass="com.google.adk.web.AdkWebServer" -Dexec.args="--adk.agents.source-dir=target --server.port=8000"
```

**Mac / Linux:**

```bash
mvn compile exec:java \
    -Dexec.mainClass="com.google.adk.web.AdkWebServer" \
    -Dexec.args="--adk.agents.source-dir=target --server.port=8000"
```

Then open [http://localhost:8000](http://localhost:8000) in your browser.

## Supported Cities

Mumbai, Navi Mumbai, Delhi, Bangalore, Chennai, Hyderabad, Pune, Kolkata, New York, Los Angeles, San Francisco, Chicago, Toronto, London, Paris, Berlin, Moscow, Istanbul, Cairo, Dubai, Singapore, Bangkok, Shanghai, Hong Kong, Seoul, Tokyo, Sydney.

## Built With

- [Google ADK for Java](https://adk.dev/get-started/java/) — Agent Development Kit
- [Gemini 2.5 Flash](https://ai.google.dev/) — Language model
