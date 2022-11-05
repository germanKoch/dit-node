# Message Structure

- HEADERS
- PARAMS
- VARS
- FOOTER

Principle:
- Each node do not have unobvious side effects (user always knows what node will change in the message)
- Context variables can be injected in all node specific fields
- params == ?
- invocation method should be always POST! invocation method inside the chain should be defined in the Node.


