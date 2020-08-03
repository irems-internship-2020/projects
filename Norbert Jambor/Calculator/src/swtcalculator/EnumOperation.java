package swtcalculator;

class EnumOperation {
	
	public enum Operation {
		PLUS("+"), MINUS("-"), MULTIPLICATION("*"), DIVISION("/");

		private String text;

		Operation(final String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

}