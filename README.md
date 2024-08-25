# PlanRadar Integration Automation

## Overview

This project is an automation script developed using Java Spring Boot to integrate with the PlanRadar SaaS application. The script automates tasks triggered upon the creation of a new project in PlanRadar:

1. **Add a Form**: Automatically create and add it form to the new project.
2. **Add a Component (Layer)**: Creates an empty layer (component) in the new project.
3. **Create a Ticket**: Generates a ticket on the newly created layer using the IDs of the created form and layer.

## Project Purpose

PlanRadar is a construction documentation and defect management SaaS application. Customers frequently create tickets to report defects and assign them to project members for resolution. This automation script simplifies and speeds up the ticket creation process by automating actions upon project creation.

## Requirements

- **Java**: Version 11 or higher
- **Spring Boot**: Version 3.x
- **Maven**: For managing project dependencies
- **PlanRadar Account**: A trial account with API access and a personal access token.

## Setup Instructions

### 1. Clone the Repository

Clone the repository from GitHub to your local development environment:

```bash
git clone https://github.com/AliHusseiin/PlanRadar-Integration-automation-assessment.git
cd PlanRadar-Integration-automation-assessment
```
