
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Main extends JPanel {

    private int wins = 0; //win counter
    private int losses = 0; //loss counter
    private boolean b_double = false; //flag for double down
    private int bank = 20;
    private JTextField bet;
    private String bet_s;
    private double d_bet;
    // constructor = place everything you see in the Main Panel for start of game
    public Main() {

        setBackground(new Color(130, 50, 40));

        setLayout(new BorderLayout(3, 3));

        // using BorderLayout for the Main JPanel - EAST, WEST, and NORTH not used

        // This class creates the cards you see in the center of the Main JPanel
        CardPanel board = new CardPanel(); // nested class defined below
        add(board, BorderLayout.CENTER);

        // First create a buttonPanel...
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 200, 180));
        add(buttonPanel, BorderLayout.SOUTH);

        // ...then add the buttons to the panel
        JButton higher = new JButton("Higher");
        higher.addActionListener(evt -> board.doHigher()); // method found in class CardPanel
        buttonPanel.add(higher);

        JButton lower = new JButton("Lower");
        lower.addActionListener(evt -> board.doLower()); // method found in class CarPanel
        buttonPanel.add(lower);

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(evt -> board.doNewGame()); // method found in class CardPanel
        buttonPanel.add(newGame);

        JButton double_down = new JButton("Double Down");
        double_down.addActionListener(evt -> board.doDouble()); // method found in class CarPanel
        buttonPanel.add(double_down);

        // create a red border around the Main JPanel
        setBorder(BorderFactory.createLineBorder(new Color(130, 50, 40), 3));

        bet = new JTextField("bet [only numbers]",10);
        buttonPanel.add(bet);


    } // end constructor

    public static void main(String[] args) {
        JFrame window = new JFrame("High/Low Card Game");
        Main content = new Main();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120, 70);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);

    }

    /**
     * A nested class that displays the cards and does all the work
     * of keeping track of the state and responding to user events.
     */

    private class CardPanel extends JPanel {


        Deck deck; // A deck of cards to be used in the game.
        Hand hand; // The cards that have been dealt.
        String message; // A message drawn on the canvas, which changes
        // to reflect the state of the game.
        Hand d_hand; //Double down hand
        Deck d_deck; //Double down deck

        boolean gameInProgress; // Set to true when a game begins and to false
        // when the game ends.

        Font bigFont; // Font that will be used to display the message.
        Font smallFont; // Font that will be used to draw the cards.


        CardPanel() {
            setBackground(new Color(0, 120, 0));
            setForeground(Color.GREEN);
            smallFont = new Font("SansSerif", Font.PLAIN, 12);
            bigFont = new Font("Serif", Font.BOLD, 14);
            setPreferredSize(new Dimension(450, 300));
            b_double = false; //Reinitialize Double down flag
            deck = new Deck(); // Create the deck and hand to use for this game.
            hand = new Hand(); //Create a new hand for use in this game
            deck.shuffle(); //Shuffle the deck
            hand.addCard(deck.dealCard()); // Deal the first card into the hand.
            message = "Is the next card higher or lower?";
            gameInProgress = true;

        } // end constructor

        /**
         * Called when the user clicks the "Higher" button.
         * Check the user's prediction. Game ends if user guessed
         * wrong or if the user has made three correct predictions.
         */
        void doHigher() {
//            if (gameInProgress == false) {
//                // If the game has ended, it was an error to click "Higher",
//                // So set up an error message and abort processing.
//                message = "Click \"New Game\" to start a new game!";
//                repaint();
//                return;
//            }
//            hand.addCard(deck.dealCard()); // Deal a card to the hand.
//            int cardCt = hand.getCardCount();
//            Card thisCard = hand.getCard(cardCt - 1); // Card just dealt.
//            Card prevCard = hand.getCard(cardCt - 2); // The previous card.
//            if (thisCard.getValue() < prevCard.getValue()) {
//                gameInProgress = false;
//                message = "Too bad! You lose.";
//                losses++;
//            } else if (thisCard.getValue() == prevCard.getValue()) {
//                gameInProgress = false;
//                message = "Too bad!  You lose on ties.";
//                losses++;
//            } else if (cardCt == 4) {
//                gameInProgress = false;
//                message = "You win!  You made three correct guesses.";
//                wins++;
//            } else {
//                message = "Got it right!  Try for " + cardCt + ".";
//            }
//code that was used
//            if (gameInProgress == false) {
//                // If the game has ended, it was an error to click "Lower",
//                // So set up an error message and abort processing.
//                message = "Click \"New Game\" to start a new game!";
//                repaint();
//                return;
//            }
//
//            //if the game is in progress
//            hand.addCard(deck.dealCard()); // Deal a card to the hand.
//
//            int cardCt = hand.getCardCount();
//            Card thisCard = hand.getCard(cardCt - 1); // Card just dealt.
//            Card prevCard = hand.getCard(cardCt - 2); // The previous card.
//
//
//            if(b_double == true) {
//                d_hand.addCard(d_deck.dealCard());
//                Card d_thisCard = d_hand.getCard(cardCt-1);
//                Card d_prevCard = d_hand.getCard(cardCt-2);
//                boolean isHigher= thisCard.getValue() > prevCard.getValue() && d_thisCard.getValue() > d_prevCard.getValue();
//
//                if (isHigher && cardCt == 4) { //higher guess is true and game over
//                    gameInProgress = false;
//                    message = "You win!  You made three correct guesses.";
//                    wins++;
//                } else if (isHigher) { //higher guess is true and game continues
//                    message = "Got it right!  Try for " + cardCt + ".";
//                } else {//higher guess is false and you lose. Game over.
//                    gameInProgress = false;
//                    message = "Too bad! You lose.";
//                    losses++;
//                }
//                //repaint();
//            } else{
//                if (thisCard.getValue() < prevCard.getValue()) { //guess is wrong, you lose, game over
//                    gameInProgress = false;
//                    message = "Too bad! You lose.";
//                    losses++;
//                } else if (thisCard.getValue() == prevCard.getValue()) { //guess is wrong, its a tie, you lose, game over
//                    gameInProgress = false;
//                    message = "Too bad!  You lose on ties.";
//                    losses++;
//                } else if (cardCt == 4) { //guess is correct, game is over, you win.
//                    gameInProgress = false;
//                    message = "You win!  You made three correct guesses.";
//                    wins++;
//                } else { //guess is correct, continue game
//                    message = "Got it right!  Try for " + cardCt + ".";
//                }
//                //repaint();
//            }
//
//            repaint();
            evaluateGuess("Higher");
        } // end doHigher()

        /**
         * Called when the user clicks the "Lower" button.
         * Check the user's prediction. Game ends if user guessed
         * wrong or if the user has made three correct predictions.
         */

        void doDouble() {
            b_double = true;
            int cardCt = hand.getCardCount();
            if (cardCt == 1) {
                d_deck = new Deck(); // Create the deck and hand to use for this game.
                d_hand = new Hand();
                d_deck.shuffle();
                d_hand.addCard(d_deck.dealCard()); // Deal the first card into the hand.
                message = "Doubled down. Is the next card higher or lower?";
                repaint();

            } // end if cardct
        }// end doDouble

        void evaluateGuess(String btnClicked)
        {
            if (!gameInProgress)
            {
                // If the game has ended, it was an error to click "Lower",
                // So set up an error message and abort processing.
                message = "Click \"New Game\" to start a new game!";
                repaint();
                return;
            }

            bet_s = bet.getText();
            d_bet = Double.parseDouble(bet_s);
            hand.addCard(deck.dealCard()); // Deal a card to the hand.

            int cardCt = hand.getCardCount();
            Card thisCard = hand.getCard(cardCt - 1); // Card just dealt.
            Card prevCard = hand.getCard(cardCt - 2); // The previous card.

            boolean isGuessCorrect = false;
            boolean isGuessTied = false;

            //If Lower button was clicked
            if(btnClicked == "Lower")
            {
                if(b_double)
                {
                    d_hand.addCard(d_deck.dealCard());
                    Card d_thisCard = d_hand.getCard(cardCt - 1);
                    Card d_prevCard = d_hand.getCard(cardCt - 2);
                    isGuessCorrect = thisCard.getValue() < prevCard.getValue() && d_thisCard.getValue() < d_prevCard.getValue();
                    isGuessTied = thisCard.getValue() == prevCard.getValue() && d_thisCard.getValue() == d_prevCard.getValue();

                }
                else
                {
                    isGuessCorrect = thisCard.getValue() < prevCard.getValue();
                    isGuessTied = thisCard.getValue() == prevCard.getValue();
                }
            }

            //If higher button was clicked
            if(btnClicked == "Higher")
            {
                if(b_double)
                {
                    d_hand.addCard(d_deck.dealCard());
                    Card d_thisCard = d_hand.getCard(cardCt - 1);
                    Card d_prevCard = d_hand.getCard(cardCt - 2);
                    isGuessCorrect = thisCard.getValue() > prevCard.getValue() && d_thisCard.getValue() > d_prevCard.getValue();
                    isGuessTied = thisCard.getValue() == prevCard.getValue() && d_thisCard.getValue() == d_prevCard.getValue();
                }
                else
                {
                    isGuessCorrect = thisCard.getValue() > prevCard.getValue();
                    isGuessTied = thisCard.getValue() == prevCard.getValue();
                }
            }


            if (isGuessCorrect && cardCt == 4)
            { //Guess is true and game over
                gameInProgress = false;
                message = "You win!  You made three correct guesses.";
                wins++;
                bank += d_bet;
            } else if (isGuessCorrect)
            { //Guess is true and game continues
                message = "Got it right!  Try for " + cardCt + ".";
            } else if(isGuessTied)
            {
                gameInProgress = false;
                message = "Too bad!  You lose on ties.";
                losses++;
                bank -= d_bet;
            } else
            {//Guess is false and you lose. Game over.
                gameInProgress = false;
                message = "Too bad! You lose.";
                losses++;
                bank -= d_bet;
            }
            repaint();
        } // end doLower()


        void doLower() {
//            if (gameInProgress == false) {
//                // If the game has ended, it was an error to click "Lower",
//                // So set up an error message and abort processing.
//                message = "Click \"New Game\" to start a new game!";
//                repaint();
//                return;
//            }
//
//            //if the game is in progress
//            hand.addCard(deck.dealCard()); // Deal a card to the hand.
//
//            int cardCt = hand.getCardCount();
//            Card thisCard = hand.getCard(cardCt - 1); // Card just dealt.
//            Card prevCard = hand.getCard(cardCt - 2); // The previous card.
//
//
//            if(b_double == true) {
//                d_hand.addCard(d_deck.dealCard());
//                Card d_thisCard = d_hand.getCard(cardCt-1);
//                Card d_prevCard = d_hand.getCard(cardCt-2);
//                boolean isLower = thisCard.getValue() < prevCard.getValue() && d_thisCard.getValue() < d_prevCard.getValue();
//
//                if (isLower && cardCt == 4) { //Lower guess is true and game over
//                    gameInProgress = false;
//                    message = "You win!  You made three correct guesses.";
//                    wins++;
//                } else if (isLower) { //Lower guess is true and game continues
//                    message = "Got it right!  Try for " + cardCt + ".";
//                } else {//Lower guess is false and you lose. Game over.
//                    gameInProgress = false;
//                    message = "Too bad! You lose.";
//                    losses++;
//                }
//
//            } else{
//                if (thisCard.getValue() > prevCard.getValue()) { //guess is wrong, you lose, game over
//                    gameInProgress = false;
//                    message = "Too bad! You lose.";
//                    losses++;
//                } else if (thisCard.getValue() == prevCard.getValue()) { //guess is wrong, its a tie, you lose, game over
//                    gameInProgress = false;
//                    message = "Too bad!  You lose on ties.";
//                    losses++;
//                } else if (cardCt == 4) { //guess is correct, game is over, you win.
//                    gameInProgress = false;
//                    message = "You win!  You made three correct guesses.";
//                    wins++;
//                } else { //guess is correct, continue game
//                    message = "Got it right!  Try for " + cardCt + ".";
//                }
//
//            }
//            repaint();
            evaluateGuess("Lower");
        } // end doLower()

        /**
         * Called by the constructor, and called when
         * the user clicks the "New Game" button. Starts a new game.
         */
        void doNewGame()
        {

            if (gameInProgress)
            {
                // If the current game is not over, it is an error to try
                // to start a new game.
                message = "You still have to finish this game!";
                repaint();
                return;
            }
            bet_s = bet.getText();
            d_bet = Double.parseDouble(bet_s);
            if (bank >= 100) {
                message = "You have too much money for the bank!";
                repaint();
                return;
            }
            else if(bank <= 0)
            {
                message = "You are broke :[";
                repaint();
                return;
            }
            else
            {
                b_double = false; //Reinitialize Double down flag
                deck = new Deck(); // Create the deck and hand to use for this game.
                hand = new Hand(); //Create a new hand for use in this game
                deck.shuffle(); //Shuffle the deck
                hand.addCard(deck.dealCard()); // Deal the first card into the hand.
                message = "Is the next card higher or lower?";
                gameInProgress = true;
            }
            repaint();
        } // end doNewGame()

        /**
         * This method draws the message at the bottom of the
         * panel, and it draws all the dealt cards spread out
         * across the canvas. If the game is in progress, an extra
         * card is drawn face down representing the card to be dealt next.
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(bigFont);
            g.drawString(message, 10, 260);
            g.setFont(smallFont);
            g.drawString("losses: " + losses, 10, 285);
            g.drawString("wins: " + wins, 330, 285);
            g.drawString("money: $" + bank, 150, 285);
            g.setFont(smallFont);
            int cardCt = hand.getCardCount();
            for (int i = 0; i < cardCt; i++) {
                drawCard(g, hand.getCard(i), 10 + i * 90, 10);
                if(b_double)
                    drawCard(g, d_hand.getCard(i), 10 + i * 90, 130);
            }
            if (gameInProgress) {
                drawCard(g, null, 10 + cardCt * 90, 10);
                if(b_double)
                    drawCard(g, null, 10 + cardCt * 90, 130);
            }
        } // end paintComponent()

        /**
         * Draws a card as a 80 by 100 rectangle with upper left corner at (x,y).
         * The card is drawn in the graphics context g. If card is null, then
         * a face-down card is drawn.
         */
        void drawCard(Graphics g, Card card, int x, int y) {
            if (card == null) {
                // Draw a face-down card
                g.setColor(Color.BLUE);
                g.fillRect(x, y, 80, 100);
                g.setColor(Color.WHITE);
                g.drawRect(x + 3, y + 3, 73, 93);
                g.drawRect(x + 4, y + 4, 71, 91);
            } else {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 80, 100);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, 79, 99);
                g.drawRect(x + 1, y + 1, 77, 97);
                if (card.getSuit() == Card.DIAMONDS || card.getSuit() == Card.HEARTS)
                    g.setColor(Color.RED);
                else
                    g.setColor(Color.BLACK);
                g.drawString(card.getValueAsString(), x + 10, y + 30);
                g.drawString("of", x + 10, y + 50);
                g.drawString(card.getSuitAsString(), x + 10, y + 70);
            }

//            int y_offset = 120;
//            //If this is double, we need to create another row of cards
//            if(b_double == true) {
//                if (card == null) {
//                    // Draw a face-down card
//
//                    g.setColor(Color.BLUE);
//                    g.fillRect(x, y+y_offset, 80, 100);
//                    g.setColor(Color.WHITE);
//                    g.drawRect(x + 3, y + y_offset+3, 73, 93);
//                    g.drawRect(x + 4, y + y_offset+4, 71, 91);
//                } else {
//                    g.setColor(Color.WHITE);
//                    g.fillRect(x, y+y_offset, 80, 100);
//                    g.setColor(Color.GRAY);
//                    g.drawRect(x, y+y_offset, 79, 99);
//                    g.drawRect(x + 1, y + y_offset + 1, 77, 97);
//                    if (card.getSuit() == Card.DIAMONDS || card.getSuit() == Card.HEARTS)
//                        g.setColor(Color.RED);
//                    else
//                        g.setColor(Color.BLACK);
//                    g.drawString(card.getValueAsString(), x + 10, y + y_offset + 30);
//                    g.drawString("of", x + 10, y + y_offset+50);
//                    g.drawString(card.getSuitAsString(), x + 10, y + 70+y_offset);
//                }
//            }


//            if (b_double == true && card != null) {
//
//                // Draw a face-down card
//                g.setColor(Color.BLUE);
//                g.fillRect(x + 90, y + 120, 80, 100);
//                g.setColor(Color.WHITE);
//                g.drawRect(x + 90 + 3, y + 123, 73, 93);
//                g.drawRect(x + 90 + 4, y + 124, 71, 91);
//                g.setColor(Color.WHITE);
//                g.fillRect(x, y + 120, 80, 100);
//                g.setColor(Color.GRAY);
//                g.drawRect(x, y + 120, 79, 99);
//                g.drawRect(x + 1, y + 121, 77, 97);
//                if (card.getSuit() == Card.DIAMONDS || card.getSuit() == Card.HEARTS)
//                    g.setColor(Color.RED);
//                else
//                    g.setColor(Color.BLACK);
//                g.drawString(card.getValueAsString(), x + 10, y + 150);
//                g.drawString("of", x + 10, y + 170);
//                g.drawString(card.getSuitAsString(), x + 10, y + 190);
//            }
        } // end drawCard()

    } // end nested class CardPanel

} // end class Main