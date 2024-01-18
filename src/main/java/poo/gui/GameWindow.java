package poo.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import poo.modelo.CardDeck;
import poo.modelo.Game;
import poo.modelo.GameEvent;
import poo.modelo.GameListener;

public class GameWindow extends Application implements GameListener {

	public static void main(String[] args) {
		launch(args);
	}

	private static Tab tab1;
    private static Tab tab2;
    private Tab tab3;

	@Override
	public void start(Stage primaryStage) {
		Game.getGame().addGameListener(this);

		primaryStage.setTitle("JokenPokemon");

		Group root = new Group();

        TabPane tabPane = new TabPane();

        tab1 = new Tab("Jogador 1");
    	tab2 = new Tab("Jogador 2");
        tab3 = new Tab("Mesa");
        //Tab tab4 = new Tab("Mesa Jogador 2");

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);


		GridPane grid1 = new GridPane();
		grid1.setAlignment(Pos.CENTER);
		grid1.setHgap(10);
		grid1.setVgap(10);
		grid1.setPadding(new Insets(25, 25, 25, 25));

		DeckView deckJ1 = new DeckView(1);
		ScrollPane sd1 = new ScrollPane();
		sd1.setPrefSize(1200, 250);
		sd1.setContent(deckJ1);
		grid1.add(sd1, 0, 0);

		//Button retornaJ1 = new Button("Retorne");
		//grid1.add(retornaJ1, 1, 1);
		//retornaJ1.setOnAction(e -> Game.getGame().getRetorne());
		
		Button selecionaJ1 = new Button("Selecionado");
		grid1.add(selecionaJ1, 2, 1);
		selecionaJ1.setOnAction(e -> Game.getGame().selecionado(true));

		GridPane grid2 = new GridPane();
		grid2.setAlignment(Pos.CENTER);
		grid2.setHgap(10);
		grid2.setVgap(10);
		grid2.setPadding(new Insets(25, 25, 25, 25));

		DeckView deckJ2 = new DeckView(2);
		ScrollPane sd2 = new ScrollPane();
		sd2.setPrefSize(1200, 200);
		sd2.setContent(deckJ2);
		//sd2.getContent().setScaleX(2);
		//sd2.getContent().setScaleY(2);
		//sd2.setScaleX(1.25);
		//sd2.setScaleY(1.25);
		grid2.add(sd2, 0, 2);

		//Button retornaJ2 = new Button("Retorne");
		//grid2.add(retornaJ2, 1, 1);
		//retornaJ2.setOnAction(e -> Game.getGame().getRetorne());
		
		Button selecionaJ2 = new Button("Selecionar");
		grid2.add(selecionaJ2, 2, 1);
		selecionaJ2.setOnAction(e -> Game.getGame().selecionado(true));


		GridPane grid3 = new GridPane();
		grid3.setAlignment(Pos.CENTER);
		grid3.setHgap(10);
		grid3.setVgap(10);
		grid3.setPadding(new Insets(25, 25, 25, 25));

		DeckView mesaJ1 = new DeckView(-1);
		ScrollPane sdM1 = new ScrollPane();
		
		sdM1.setPrefSize(200, 250);
		sdM1.setContent(mesaJ1);
		grid3.add(sdM1, 1, 0);
		
		//grid3.add(sd1, 2, 0);
		//grid3.add(sd2, 1, 0);
		//grid3.add(sd2, 1, 2);

		PlacarView placar = new PlacarView();
		grid3.add(placar, 0, 1);

		Button butClean = new Button("Batalha");
		grid3.add(butClean, 1, 1);
		butClean.setOnAction(e -> Game.getGame().removeSelected());
		
		Button novoT = new Button("Proximo Turno");
		grid3.add(novoT, 2, 1);
		novoT.setOnAction(e -> Game.getGame().novoTurno());

		DeckView mesaJ2 = new DeckView(-2);
		ScrollPane sdM2 = new ScrollPane();
		sdM2.setPrefSize(200, 250);
		sdM2.setContent(mesaJ2);
		grid3.add(sdM2, 1, 2);

		tab1.setContent(grid1);
        tab2.setContent(grid2);
        tab3.setContent(grid3);


		root.getChildren().add(tabPane);
		
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public static void atualizaMao1(){
		GridPane gridA = new GridPane();
		gridA.setAlignment(Pos.CENTER);
		gridA.setHgap(10);
		gridA.setVgap(10);
		gridA.setPadding(new Insets(25, 25, 25, 25));

		Button selecionaJ1 = new Button("Selecionado");
		gridA.add(selecionaJ1, 2, 1);
		selecionaJ1.setOnAction(e -> Game.getGame().selecionado(true));

		DeckView deckJ1 = new DeckView(1);
		ScrollPane sd1 = new ScrollPane();
		sd1.setPrefSize(1200, 250);
		sd1.setContent(deckJ1);
		gridA.add(sd1, 0, 0);
		tab1.setContent(gridA);
	}

	public static void atualizaMao2(){
		GridPane gridA = new GridPane();
		gridA.setAlignment(Pos.CENTER);
		gridA.setHgap(10);
		gridA.setVgap(10);
		gridA.setPadding(new Insets(25, 25, 25, 25));

		Button selecionaJ2 = new Button("Selecionar");
		gridA.add(selecionaJ2, 2, 1);
		selecionaJ2.setOnAction(e -> Game.getGame().selecionado(true));

		DeckView deckJ1 = new DeckView(2);
		ScrollPane sd1 = new ScrollPane();
		sd1.setPrefSize(1200, 250);
		sd1.setContent(deckJ1);
		gridA.add(sd1, 0, 0);
		tab2.setContent(gridA);
	}

	@Override
	public void notify(GameEvent eg) {
		Alert alert;
		if (eg == null) return;
		if (eg.getTarget() == GameEvent.Target.GWIN) {
			switch (eg.getAction()) {
			case INVPLAY:
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Atenção !!");
				alert.setHeaderText("Jogada inválida!!");
				alert.setContentText("Era a vez do jogador " + eg.getArg());
				alert.showAndWait();
				break;
			case MUSTCLEAN:
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Atenção !!");
				alert.setHeaderText(null);
				alert.setContentText("Erro desconhecido!");
				alert.showAndWait();
				break;
			case ENDGAME:
			String text = "Fim de Jogo!!\n";
			if (Game.getGame().getDeckJ2().getNumberOfCards() == 0) {
				text += "O jogador 1 ganhou!!!";
			}else if(Game.getGame().getDeckJ1().getNumberOfCards() == 0){
				text += "O jogador 2 ganhou!!!";
			}
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Parabens!!");
				alert.setHeaderText(null);
				alert.setContentText(text);
				alert.showAndWait();
				break;
			case REMOVESEL:
				// Esse evento não vem para cá
			}
		}
	}

}
