package medexpress.model;

/* You could have multiple question types here such as TEXT or MULTIPLE_CHOICE but for simplicity I've opted to only
* accept booleans as the logic to handle whether we give them a prescription or not will be easier to implement within
* the time I've been given
*/
public enum QuestionType {
    BOOLEAN
}
