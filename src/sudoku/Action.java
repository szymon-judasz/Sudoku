package sudoku;
public enum Action {
	TRYING_TO_SET_VAL,
	VALUE_SET,
	TRYING_TO_RESET_VAL, //usused
	VALUE_RESET, //usuned
        TRYING_TO_LESSEN_CONFLICT // making conflict false
	
}
// to do - make conflict beans