#!/bin/bash

# Ensure the script exits if any command fails
set -e

# Set the API endpoint URL
API_URL="http://localhost:8080/api_v1/report/generate"

# Set the HTTP method (GET, POST, PUT, DELETE, etc.)
HTTP_METHOD="POST"

# Optional: Set additional headers if needed
HEADERS="-H 'Content-Type: application/json'"

# Send the HTTP request using curl and capture the response and HTTP status code
RESPONSE=$(curl -s -X $HTTP_METHOD $HEADERS -w "\n%{http_code}" $API_URL)

# Extract the HTTP status code from the response
HTTP_STATUS=$(echo "$RESPONSE" | tail -n1)

# Extract the response body by removing the last line (HTTP status code)
RESPONSE_BODY=$(echo "$RESPONSE" | sed '$d')

# Print the response and the HTTP status code
echo
echo "HTTP Status Code: $HTTP_STATUS"
echo "Response from API: $RESPONSE_BODY"
