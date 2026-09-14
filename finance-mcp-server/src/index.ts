import { McpServer } from "@modelcontextprotocol/server";
import { process } from "@modelcontextprotocol/server/_shims";
import { serveStdio } from "@modelcontextprotocol/server/stdio";
import * as z from "zod/v4";


const FINANCE_API_URL =
    process.env.FINANCE_API_URL ??
    "http://localhost:8080/api/expenses";

function createServer(): McpServer {

    const server = new McpServer({
        name: "personal-finance-mcp",
        version: "1.0.0"
    });

    server.registerTool(
        "list_expenses",
        {
            description:
                "List all expenses from the Personal Finance application",

            inputSchema: z.object({})
        },

        async () => {

            try {

                const response = await fetch(FINANCE_API_URL);

                if (!response.ok) {
                    return {
                        content: [
                            {
                                type: "text",
                                text:
                                    `Finance API returned HTTP ${response.status}`
                            }
                        ],
                        isError: true
                    };
                }

                const expenses = await response.json();

                return {
                    content: [
                        {
                            type: "text",
                            text: JSON.stringify(expenses, null, 2)
                        }
                    ]
                };

            } catch (error) {

                const message =
                    error instanceof Error
                        ? error.message
                        : String(error);

                return {
                    content: [
                        {
                            type: "text",
                            text:
                                `Unable to connect to Finance API: ${message}`
                        }
                    ],
                    isError: true
                };
            }
        }
    );

    return server;
}

serveStdio(createServer);