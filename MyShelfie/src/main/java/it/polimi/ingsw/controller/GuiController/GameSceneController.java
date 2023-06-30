package it.polimi.ingsw.controller.GuiController;

import it.polimi.ingsw.View.Gui;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.Events.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;

import java.util.List;

/**
 * This class implements the main scene of the game, where each match is played.
 */
public class GameSceneController {

    private Gui gui;

    private boolean isSelectingColumn = false;
    private boolean isSelectingTiles = false;
    int tilesToPicNumber;
    int pickedTilesNumber;
    private int[] pickedRow;
    private int[] pickedColumn;

    private int points = 0;

    boolean first = true;

    @FXML
    private Button showpointButton;
    @FXML
    private Button updateShelfieButton;
    @FXML
    private Button selectTilesButton;
    @FXML
    private Button chatButton;
    @FXML
    private Button commonGoalButton;
    @FXML
    private Button personalGoalButton;

    @FXML
    private GridPane chatGrid;

    @FXML
    private Label turnLabel;
    @FXML
    private GridPane tilesNumberGrid;

    @FXML
    private GridPane tilesPositionGrid;
    @FXML
    private Button selectTilesPositionButton;
    @FXML
    private Button selectTilesPositionButton2;
    @FXML
    private Button selectTilesPositionButton3;

    @FXML
    private GridPane boardGrid;
    @FXML
    private GridPane playerShelfie;

    public void setGUI(Gui gui){
        this.gui = gui;
    }

    public void turnManager(){
        if(first){
            showTurnButton(true);
            first = false;
        }
    }

    /**
     * At the end of each turn this method update the board by removing
     * the tiles that the player selected.
     *
     * @param board that needs to be modified.
     */
    public void updateBoard(Tile[][] board){

        double cellHeight = boardGrid.getCellBounds(0,0).getHeight()-10;
        double cellWidth = boardGrid.getCellBounds(0,0).getWidth()-10;

        boardGrid.setAlignment(Pos.CENTER);
        boardGrid.setPadding(new Insets(10));
        boardGrid.getChildren().clear();

        for(int row = 0; row < board.length; row++){
            for(int column = 0; column < board.length; column++){
                if(board[row][column] != null){
                    //System.out.println("color:" + (board[column][row]).getColor().parseToString(board[column][row].getColor()));
                    Button button = new Button();
                    button.setPrefSize(cellWidth, cellHeight);
                    int buttonRow = row;
                    int buttonColumn = column;
                    button.setOnAction(actionEvent -> {
                        if(pickedTilesNumber < tilesToPicNumber){
                            //button.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                            pickedRow[pickedTilesNumber] = buttonRow;
                            pickedColumn[pickedTilesNumber] = buttonColumn;
                            pickedTilesNumber++;
                        }
                        if(pickedTilesNumber == tilesToPicNumber && tilesToPicNumber != 0){
                            gui.sendTilesPositionOnBoard(pickedRow, pickedColumn);
                            pickedTilesNumber = 0;
                            tilesToPicNumber = 0;
                        }
                    });
                    Image image = getTileImage((board[row][column]).getColor());
                    if(image != null){
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(cellWidth);
                        imageView.setFitHeight(cellHeight);
                        button.setGraphic(imageView);
                    }else{
                        button.setGraphic(null);
                        button.setVisible(false);
                    }
                    button.setPadding(new Insets(0));
                    boardGrid.add(button,column,row);
                }else{
                    //System.out.println("null");
                }
            }
        }
    }

    /**
     * This method update the personal shelfie by
     * adding the tiles that the player selected.
     *
     * @param shelfie that needs to be updated.
     */
    public void updatePlayerShelfie(Tile[][] shelfie){
        double cellHeight = playerShelfie.getCellBounds(0,0).getHeight()-10;
        double cellWidth = playerShelfie.getCellBounds(0,0).getWidth()-10;

        playerShelfie.setAlignment(Pos.CENTER);
        playerShelfie.setPadding(new Insets(10));
        playerShelfie.getChildren().clear();

        for(int column = 0; column < 6; column++){
            for(int row = 0; row < 5; row++){
                if(shelfie[column][row] != null){
                    //System.out.println("color:" + (shelfie[column][row]).getColor().parseToString(shelfie[column][row].getColor()));
                    Image image = getTileImage((shelfie[column][row]).getColor());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(cellWidth);
                    imageView.setFitHeight(cellHeight);
                    playerShelfie.add(imageView,row,column);
                }else{
                    //System.out.println("null");
                }
            }
        }
    }

    /**
     * This method is set to ask the player how many tiles
     * he wants to select.
     */
    public void askTilesNumber(){
        turnLabel.setVisible(true);
        tilesNumberGrid.setVisible(true);
        turnLabel.setText("How many tiles you want to pick?");
    }

    /**
     *
     * @param event
     */
    @FXML
    private void pickTile(ActionEvent event){
        int number = Integer.parseInt(((Button) event.getSource()).getText());
        gui.sendTilesNumber(number);
        tilesNumberGrid.setVisible(false);
    }

    public void askTilesPositionOnBoard(int tilesNumber){
        turnLabel.setVisible(true);
        tilesToPicNumber = tilesNumber; //in caso di selezione errata così il conteggio riparte
        turnLabel.setText("Select " + tilesToPicNumber + " tiles on the board");
        pickedRow = new int[tilesToPicNumber];
        pickedColumn = new int[tilesToPicNumber];
    }

    /**
     * This method is set to ask the player the column of his shelfie where
     * he wants to put his selected tiles.
     */
    public void askShelfieColumn(){
        isSelectingColumn = true;
        turnLabel.setVisible(true);
        turnLabel.setText("In which column you want to put your tiles?");
    }

    /**
     * This method handles the click of the button of the column of the shelfie.
     * @param event button clicked.
     */
    @FXML
    private void clickOnColumn(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if(isSelectingColumn){
            gui.sendShelfieColumn(Integer.parseInt(clickedButton.getText()));
            isSelectingColumn = false;
        }
        //i pulsanti della colonna hanno come testo il numero della colonna (e visibilità impostata su zero)
    }

    /**
     * This method ask the player the order of positioning in the selected column of
     * his shelfie of the tiles he has selected.
     *
     * @param tileList tiles that the player selected.
     */
    public void askTilesOrder(List<Tile> tileList){
        turnLabel.setVisible(true);
        turnLabel.setText("Which tile do you want to insert?");
        tilesPositionGrid.setVisible(true);

        if(tileList.size() >= 1){
            selectTilesPositionButton.setVisible(true);
            Image image = getTileImage(tileList.get(0).getColor());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);
            selectTilesPositionButton.setGraphic(imageView);
            selectTilesPositionButton.setPadding(new Insets(0));
        }else{
            selectTilesPositionButton.setVisible(false);
            selectTilesPositionButton.setGraphic(null);
        }

        if(tileList.size() >= 2){
            selectTilesPositionButton2.setVisible(true);
            Image image = getTileImage(tileList.get(1).getColor());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);
            selectTilesPositionButton2.setGraphic(imageView);
            selectTilesPositionButton2.setPadding(new Insets(1));
        }else{
            selectTilesPositionButton2.setVisible(false);
            selectTilesPositionButton2.setGraphic(null);
        }

        if(tileList.size() >= 3){
            selectTilesPositionButton3.setVisible(true);
            Image image = getTileImage(tileList.get(2).getColor());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);
            selectTilesPositionButton3.setGraphic(imageView);
            selectTilesPositionButton3.setPadding(new Insets(2));
        }else{
            selectTilesPositionButton3.setVisible(false);
            selectTilesPositionButton3.setGraphic(null);
        }

    }

    /**
     * GIORGIO
     * @param event
     */
    @FXML
    private void pickTilePosition(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        tilesPositionGrid.setVisible(false);
        gui.sendPlacingTile(Integer.parseInt(clickedButton.getText()) - 1);
    }

    /**
     * At the end of each turn this method handles the alert that shows
     * the player the points he has.
     *
     * @param points of the player that is ending his turn.
     * @param endOfTurn end of player's turn.
     */
    public void showPoints(int points, boolean endOfTurn){
        if(endOfTurn){
            this.points = points;
            endOfTurn();
        }
       showAlert("", "You have: " + points + " points");
    }

    /**
     * This method handles the end of turn.
     */
    private void endOfTurn(){
        showTurnButton(false);
        turnLabel.setVisible(false);
        tilesPositionGrid.setVisible(false);
        first = true;
    }

    /**
     * GIORGIO
     * @param show
     */
    public void showTurnButton(boolean show){
        chatButton.setVisible(show);
        updateShelfieButton.setVisible(show);
        selectTilesButton.setVisible(show);
        showpointButton.setVisible(show);
        commonGoalButton.setVisible(show);
        personalGoalButton.setVisible(show);
    }

    /**
     * This method handles the button that allows the player to chat.
     *
     * @param event button clicked.
     */
    @FXML
    private void chatButtonAction(ActionEvent event){

        gui.sendTurnSelection(5);

        if(chatGrid.isVisible()){
            chatGrid.setVisible(false);
        }else{
            chatGrid.setVisible(false);
        }
    }

    /**
     * This method handles the button that allows the player to start selecting
     * tiles from the board.
     *
     * @param event button clicked.
     */
    @FXML
    private void selectTilesButtonAction(ActionEvent event){
        gui.sendTurnSelection(1);
        showTurnButton(false);
    }


    /**
     * This button handles the button that shows the player his points.
     *
     * @param event button clicked.
     */
    @FXML
    private void showPointButtonAction(ActionEvent event){
        showPoints(points, false);
    }

    /**
     * This method handles the button that shows the shelfie of the
     * other players.
     *
     * @param event button clicked.
     */
    @FXML
    private void showOthersShelfieButtonAction(ActionEvent event){
        gui.sendTurnSelection(3);
    }

    /**
     * This method handles the button that shows to the player
     * the common goals of the match.
     *
     * @param event button clicked.
     */
    @FXML
    private void showCommonGoalButtonAction(ActionEvent event){
        gui.sendTurnSelection(4);
    }

    /**
     * This method (select two commongoals at the start of the game)? GIORGIO
     * @param name
     */
    public void updateCommonGoals(CommonGoalsName name){
        String goalName = "";
        if(name != null){
            switch(name) {
                case CORNERS:
                    goalName = ("Four tiles of the same type in the four corners of the bookshelf. ");
                    break;
                case COUPLES:
                    goalName = ("Six groups each containing at least 2 tiles of the same type (not necessarily in the depicted shape). The tiles of one group can be different from those of another group.");
                    break;
                case CROSS:
                    goalName = ("Five tiles of the same type forming an X.");
                    break;
                case DIAGONAL:
                    goalName = ("Five tiles of the same type forming a diagonal. ");
                    break;
                case EIGHT:
                    goalName = ("Eight tiles of the same type. There’s no restriction about the position of these tiles.");
                    break;
                case FOURGROUPS:
                    goalName = ("Four groups each containing at least 4 tiles of the same type (not necessarily in the depicted shape). The tiles of one group can be different from those of another group");
                    break;
                case FOURROWS:
                    goalName = ("Four lines each formed by 5 tiles of maximum three different types. One line can show the same or a different combination of another line");
                    break;
                case SQUARES:
                    goalName = ("Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles of one square can be different from those of the other square.");
                    break;
                case STAIRS:
                    goalName = ("Five columns of increasing or decreasing height. Starting from the first column on the left or on the right, each next column must be made of exactly one more tile. Tiles can be of any type.");
                    break;
                case THREE:
                    goalName = ("Three columns each formed by 6 tiles Five tiles of the same type forming an X of maximum three different types column can show the same or a different combination of another column");
                    break;
                case TWOCOLUMNS:
                    goalName = ("Two columns each formed by 6 different types of tiles.");
                    break;
                case TWOROWS:
                    goalName = ("Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line");
                    break;
            }
        }
        showAlert("Common goal", goalName);
    }

    /**
     * This method handles the button that shows to the player his personal goal.
     *
     * @param event button clicked.
     */
    @FXML
    private void showPersonalGoalButtonAction(ActionEvent event){
        if(personalGoalButton.getText().equals("End")){
            gui.sendTurnSelection(3);
            personalGoalButton.setText("Show personal goal");
            showTurnButton(true);
        }else{
            gui.sendTurnSelection(2);
            showTurnButton(false);
            personalGoalButton.setVisible(true);
            personalGoalButton.setText("End");
        }
    }

    /**
     * This method shows the alert where is nominated the winner player.
     * @param winner username of the player that has won the match.
     */
    public void showWinner(String winner){
        showAlert("Game over", winner + "is the winner!");
    }

    /**
     * GIORGIO
     * @param header
     * @param info
     */
    public void showAlert(String header, String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MyShelfie");
        alert.setHeaderText(header);
        alert.setContentText(info);
        alert.showAndWait();
    }

    /**
     * This method is set to get the image of the tiles selected.
     *
     * @param color of the tile.
     * @return image of the tile.
     */
    private Image getTileImage(Color color){
        Image image = null;
        switch (color){
            case CLEAR -> image = null;
            case BLUE -> image = new Image("assets/item tiles/Cornici1.1.png");
            case GREEN -> image = new Image("assets/item tiles/Gatti1.1.png");
            case YELLOW -> image = new Image("assets/item tiles/Giochi1.1.png");
            case WHITE -> image = new Image("assets/item tiles/Libri1.1.png");
            case PURPLE -> image = new Image("assets/item tiles/Piante1.1.png");
            case CYAN -> image = new Image("assets/item tiles/Trofei1.1.png");
        }
        return image;
    }

}


