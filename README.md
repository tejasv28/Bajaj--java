# BFHL REST API - Bajaj Finserv Health Challenge

This repository contains the backend implementation for the BFHL REST API developed in Spring Boot (Java).

## Endpoints

### 1. `POST /bfhl`
Processes the incoming array data and categorizes them into different arrays, along with additional custom logic.

**Request Body:**
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

**Response Body:**
```json
{
  "is_success": true,
  "user_id": "tejasv_sunil_dubey_16102004",
  "email": "tejasvsunil230798@acropolis.in",
  "roll_number": "0827CS231276",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

## Features
- Strict adherence to REST constraints
- DTO implementation for clean decoupling
- Unit tested to ensure precise calculation metrics
- Exception resilient logic
