package it.polimi.ingsw.controller.GuiController;

import it.polimi.ingsw.View.Gui;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Tile;
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

public class GameSceneController {

    private Gui gui;

    private int playerCounter;
    private int maxPlayer;
    private String usernameList;

    private boolean isSelectingColumn = false;
    private boolean isSelectingTiles = false;
    int tilesToPicNumber;
    int pickedTilesNumber;
    private int[] pickedRow;
    private int[] pickedColumn;

    private int turnState = 0;

    @FXML
    private ImageView personalGoal;
    @FXML
    private ImageView CommonGoals1;
    @FXML
    private ImageView CommonGoals2;

    @FXML
    private Button showpointButton;
    @FXML
    private Button otherShelfieButton;
    @FXML
    private Button selectTilesButton;
    @FXML
    private Button chatButton;

    @FXML
    private GridPane chatGrid;

    @FXML
    private Label turnLabel;
    @FXML
    private GridPane tilesNumberGrid;

    @FXML
    private GridPane boardGrid;
    @FXML
    private GridPane playerShelfie;

    public void setGUI(Gui gui){
        this.gui = gui;
    }

    public void turnManager(){
        switch (turnState){
            case 0:
                System.out.println("Starting turn");
                //showAlert("", "It's your turn!");
                System.out.println("Updating personal goal, state: " + turnState);
                gui.sendTurnSelection(2);
                showTurnButton(true);
                turnState++;
                break;
            case 1:
                System.out.println("Updating common goals, state: " + turnState);
                gui.sendTurnSelection(4);
                turnState++;
                break;
            case 2:
                System.out.println("Updating shelfie, state: " + turnState);
                gui.sendTurnSelection(3);
                turnState++;
                break;
            default:
                System.out.println("Waiting for turn selection, state: " + turnState);
        }
    }

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
                    Image image = null;
                    switch ((board[row][column]).getColor()){
                        case CLEAR -> image = null;
                        case BLUE -> image = new Image("assets/item tiles/Cornici1.1.png");
                        case GREEN -> image = new Image("assets/item tiles/Gatti1.1.png");
                        case YELLOW -> image = new Image("assets/item tiles/Giochi1.1.png");
                        case WHITE -> image = new Image("assets/item tiles/Libri1.1.png");
                        case PURPLE -> image = new Image("assets/item tiles/Piante1.1.png");
                        case CYAN -> image = new Image("assets/item tiles/Trofei1.1.png");
                    }
                    if(image != null){
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(cellWidth);  // Imposta la larghezza dell'immagine
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
                    Image image = null;
                    switch ((shelfie[column][row]).getColor()){
                        case CLEAR -> image = null;
                        case BLUE -> image = new Image("assets/item tiles/Cornici1.1.png");
                        case GREEN -> image = new Image("assets/item tiles/Gatti1.1.png");
                        case YELLOW -> image = new Image("assets/item tiles/Giochi1.1.png");
                        case WHITE -> image = new Image("assets/item tiles/Libri1.1.png");
                        case PURPLE -> image = new Image("assets/item tiles/Piante1.1.png");
                        case CYAN -> image = new Image("assets/item tiles/Trofei1.1.png");
                    }
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(cellWidth);  // Imposta la larghezza dell'immagine
                    imageView.setFitHeight(cellHeight);
                    playerShelfie.add(imageView,row,column);
                }else{
                    //System.out.println("null");
                }
            }
        }
    }

    public void updateCommonGoals(){

    }

    public void updatePoints(){

    }

    public void askTilesNumber(){
        turnLabel.setVisible(true);
        tilesNumberGrid.setVisible(true);
        turnLabel.setText("How many tiles you want to pick?");
    }

    @FXML
    private void pickTile(ActionEvent event){
        int number = Integer.parseInt(((Button) event.getSource()).getText());
        gui.sendTilesNumber(number);
        tilesToPicNumber = number;
        tilesNumberGrid.setVisible(false);
    }

    public void askTilesPositionOnBoard(){
        turnLabel.setVisible(true);
        turnLabel.setText("Select " + tilesToPicNumber + " tiles on the board");
        pickedRow = new int[tilesToPicNumber];
        pickedColumn = new int[tilesToPicNumber];
    }

    public void askShelfieColumn(){
        isSelectingColumn = true;
        turnLabel.setVisible(true);
        turnLabel.setText("In which column you want to put your tiles?");
    }

    @FXML
    private void clickOnColumn(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if(isSelectingColumn){
            gui.sendShelfieColumn(Integer.parseInt(clickedButton.getText()));
            isSelectingColumn = false;
        }
        //i pulsanti della colonna hanno come testo il numero della colonna (e visibilità impostata su zero)
    }

    public void askTilesOrder(List<Tile> tileList){
        if(tileList.size() == 1){
            //è l'ultima da mandare -> è finito il turno
            endOfTurn();
        }
        gui.sendPlacingTile(0);
    }

    private void endOfTurn(){
        showTurnButton(false);
        turnLabel.setVisible(false);
        turnState = 0;
    }

    public void showTurnButton(boolean show){
        chatButton.setVisible(show);
        otherShelfieButton.setVisible(show);
        selectTilesButton.setVisible(show);
        showpointButton.setVisible(show);
    }

    @FXML
    private void chatButtonAction(ActionEvent event){

        gui.sendTurnSelection(5);

        if(chatGrid.isVisible()){
            chatGrid.setVisible(false);
        }else{
            chatGrid.setVisible(false);
        }
    }

    @FXML
    private void selectTilesButtonAction(ActionEvent event){
        gui.sendTurnSelection(1);
        showTurnButton(false);
    }


    @FXML
    private void showPointButtonAction(ActionEvent event){

    }

    @FXML
    private void showOthersShelfieButtonAction(ActionEvent event){
        gui.sendTurnSelection(7);
    }

    public void showWinner(String winner){
        showAlert("Game over", winner + "is the winner!");
    }

    public void showAlert(String header, String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MyShelfie");
        alert.setHeaderText(header);
        alert.setContentText(info);
        alert.showAndWait();
    }
}


