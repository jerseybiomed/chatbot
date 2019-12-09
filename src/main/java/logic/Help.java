package logic;

public class Help {
	public static String banditHelp =
			"You are greeted by a One Arm Bandit\n" +
					"To play enter '/roll' and the bet amount separated by a space\n" +
					"I will output the final line and the result\n" +
					"You can see the winning lines by enter '/rules'\n" +
					"I'm meant for gamblers, so they don't lose their money\n" +
					"\nFor output of this help once again instead of roll enter '/help'";

	public static String banditRules =
			"The first class of lines are lines with three identical cells\n" +
					"Such lines increase bets by 2\n" +
					"The second class of lines are lines with two identical cells\n" +
					"Such lines increase bets by 1.5\n" +
					"The third class of lines are 'beautiful' lines\n" +
					"These lines have the same difference between adjacent cells\n" +
					"Such lines keep the bet\n" +
					"The other lines tell you of defeat";

	public static String rouletteHelp =
			"You are greeted by a Roulette\n" +
					"To play enter '/red' or '/black' and the bet amount separated by a space\n" +
					"Every 30 seconds there is a new scrolling roulette\n" +
					"After scrolling you can bet on the following by entering '/bet red' or '/bet dark' or '/bet NUMBER'" +
					"and the bet amount separated by a space (NUMBER between 0 and 36)\n" +
					"You can see the winning payment by enter '/rules'\n" +
					"Also all participants reported when someone put somewhere put a bet\n" +
					"\nFor output of this help once again instead of roll enter '/help'";

	public static String rouletteRules =
			"If you bet on the color and guess, then your bet increases by 2\n" +
					"If you bet on the number and guess, the bet increases by 36\n" +
					"If you do not guess, you lose money";
}
