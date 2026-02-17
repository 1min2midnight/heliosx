# heliosx
HeliosX

# How to run
This project can be imported to intellij and just simply run from MedExpressApp.java


# How to test
I tested endpoints on postman so you can import these curl requests there or simply run the cURL command below:

```
curl --location 'localhost:8080/v1/consultation/questionnaire/initial_consultation'
```

This can be used to test `/submit` endpoint
```
curl --location 'http://localhost:8080/v1/consultation/submit' \
--header 'Content-Type: application/json' \
--data '{
    "questionnaireCode": "initial_consultation",
    "answers": [
      {"questionId": 1, "answerId": 2},
      {"questionId": 2, "answerId": 1},
      {"questionId": 3, "answerId": 1},
      {"questionId": 4, "answerId": 1}
    ]
  }'
 ```
for the answerIds 1=yes 2=no

Should you need to take a look at the database you can login using the credentials in application.properties


# Assumptions

## Limit to only yes or no answers
I decided to limit the answers to yes or no to keep things simple and easy to validate due to the time constraint.
I did however try to leave some room for improvement by introducing QuestionType enum which could be leveraged in
the future to accept text field answers and others.


## Security
Since the customer is clicking on an advert and then is asked a series of questions I'm assuming they don't yet 
have an account yet so we are not associating these questionnaires with a user just yet since that seems to happen
later on in the flow.

therefore, I decided to not implement anything past simple validation and exception handling