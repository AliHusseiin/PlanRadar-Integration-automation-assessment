# PlanRadar Integration Automation

## Overview

This Java Spring Boot application integrates with the PlanRadar API to automate tasks related to project management. The application performs the following actions automatically when a new project is created:

1. **Create a Form**: Adds a sample form to the new project.
2. **Create a Component (Layer)**: Adds an empty layer to the new project.
3. **Create a Ticket**: Creates a ticket using the created form and component.

## Components

### 1. WebhookController

**Path**: `/webhook/v1`

The `WebhookController` handles incoming webhook requests from PlanRadar and initiates the automation process.

#### Endpoints

- **POST /project-created**

  Triggered when a new project is created. This endpoint calls the `handleWebhook` method in the `WebhookService` to perform the automation tasks.

### 2. WebhookService

Handles the logic for interacting with the PlanRadar API. It orchestrates the creation of forms, components, and tickets.

#### Methods

- **`handleWebhook()`**

  - **Description**: Processes the webhook, performs API calls to create a form, component, and ticket in the new project.
  - **Returns**: `Mono<String>` - A reactive type that provides the ID of the created ticket or an error message.

### 3. PlanRadarService

This service contains methods for making API requests to PlanRadar. It performs the CRUD operations required for the integration.

#### Methods

- **`getFirstProjectId()`**

  - **Description**: Retrieves the ID of the first project from PlanRadar. "FIRST PROJECT ID IN THE RETRIEVED LIST"  sort query param = -id to get the list of project Ids in DEC Order
  - **Returns**: `Mono<String>` - The ID of the first project.

- **`createForm()`**

  - **Description**: Creates a sample form with title and status fields.
  - **Returns**: `Mono<String>` - The ID of the created form.

- **`addFormToProject(String projectId, String ticketTypeId)`**

  - **Description**: Adds the created form to the specified project.
  - **Parameters**:
    - `projectId`: The ID of the project to which the form will be added.
    - `ticketTypeId`: The ID of the form to be added.
  - **Returns**: `Mono<String>` - The ID of the form added to the project.

- **`createComponent(String projectId, ComponentCreationRequest request)`**

  - **Description**: Creates a component (layer) in the specified project.
  - **Parameters**:
    - `projectId`: The ID of the project where the component will be created.
    - `request`: The request body containing the component details.
  - **Returns**: `Mono<String>` - The ID of the created component.

- **`createTicket(TicketCreationRequest request, String projectId)`**

  - **Description**: Creates a ticket in the specified project using the provided request data.
  - **Parameters**:
    - `request`: The request body containing ticket details.
    - `projectId`: The ID of the project where the ticket will be created.
  - **Returns**: `Mono<String>` - The ID of the created ticket.

### DTO Classes

- **ComponentCreationRequest**: Contains the details required to create a component (layer).
- **TicketCreationRequest**: Contains the details required to create a ticket.
- **FormCreationRequest**: Contains the details required to create a form.
- **AddFormToProjectRequest**: Contains the details required to add a form to a project.

## Setup Instructions

### 1. Clone the Repository

Clone the repository from GitHub to your local machine:

```bash
git clone https://github.com/AliHusseiin/PlanRadar-Integration-automation-assessment.git
cd PlanRadar-Integration-automation-assessment


## Configration

- **EDIT: application.properties** : planradar.api.token= PUT the access token, planradar.api.customer_id= put the customer_id

## Build the Project

- **Ensure Maven is installed, then build the project using**: mvn clean install

## Run the Application

- **Start the Spring Boot application with**: mvn spring-boot:run

## Webhooks

Navigate to Settings → Account → Webhooks in your PlanRadar account.
Set up a webhook to trigger the automation script whenever a project is created.

**the application webhook**: http://..../webhook/v1/project-created
```
