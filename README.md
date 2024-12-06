# Contact Email Sender (Gmail)

![AWS Lambda Active](https://img.shields.io/badge/AWS_Lambda-Active-brightgreen?logo=amazonaws&style=flat-square)


This project is a **lambda function** that is responsible for receiving contact requests and send an e-mail, through **GMAIL**, with a subject and message.
Some environment variables are required in order for this project to work properly:

 - MAIL_USERNAME: Username for email authentication on **GMAIL**;
 - MAIL_PASSWORD: Password for email authentication on **GMAIL** ( Must be an **App Password**, not the account password);
 - MAIL_RECIPIENT: Address that will receive the notification.
   
---

## Project Overview

- **Java Version**: 21
- **Build Tool**: Maven
- **Execution**: AWS Lambda

## Architecture Overview

![image](https://github.com/user-attachments/assets/4a2e376e-2473-4b8c-a33c-8f3b0f15773d)

## Technical Specifications

Besides the environment variables, this project is design to receive the following request:

- **Method**: POST
- **Content-type**: application/x-www-form-urlencoded
- **Body**:
  - String name (Contact name)
  - String email (Contanct e-mail)
  - String subject (E-mail Subject)
  - String message (Message Body)

