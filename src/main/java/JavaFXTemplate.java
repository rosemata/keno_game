import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

public class JavaFXTemplate extends Application {
	Stage Panel = new Stage();
	HashMap<String, Scene> scenes = new HashMap<String, Scene>();
	MenuBar bar;
	HBox rootH;
	VBox rootV;
	GridPane grid = new GridPane();
	Button[][] arrB = new Button[8][10];
	
	// Button List
	Button contG = new Button("Continue");
	Button contR = new Button("Next");
	Button done = new Button("Done");
	
	Text pickText = new Text();
	Text DrawNums = new Text();
	Text Nums = new Text();
	Text matches = new Text();

	
	String drawNums = "";
	String nums = "";
	String mat = "";
	// Results Text+String
	Text rM = new Text();
	Text rW = new Text();
	Text rT = new Text();
	String rm = "";
	String rw = "";
	String rt = "";
	
	String picked = "-fx-background-color: red";
	String def = "-fx-background-color: grey";
	int drawNum, drawTemp, spotNum, spotTemp, tempMatch=0, totalMatch=0, currentMoney=0, totalMoney=0;
	ArrayList<Integer> userNum = new ArrayList<>();
	ArrayList<Integer> winNum = new ArrayList<>();
	
	
//	String dark_theme = "dark_theme.jpg";
//	String bright_theme = "bright_theme.jpg";
	String theme_name = "default.png";
	

	public static void main(String[] args) {
		// starts java FX application and event loop
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Keno!");
		
		scenes.put("Intro", intro());
		scenes.put("Rules", gameRules());
		scenes.put("OddW", winOdds());
		scenes.put("Draw", draw());
		scenes.put("Spots", spots());
		scenes.put("Paused", pause());
		scenes.put("Game", game());
		scenes.put("Results", results());
		scenes.put("Fin", fin());
		
		primaryStage.setScene(scenes.get("Intro"));
		
		Panel = primaryStage;
		Panel.show();
	}
	
	// Introduction Screen
	public Scene intro() {
		BorderPane borderPane = new BorderPane();

		
		// play button
		HBox hBox_play = new HBox();
		Button play_button = new Button("Click to play!");
		play_button.setStyle("-fx-font-family: SansSerif;");
		hBox_play.getChildren().add(play_button);
		hBox_play.setAlignment(Pos.CENTER);
		hBox_play.setPadding(new Insets(0, 0, 50, 0));
		// Play Button Action to Pick Drawings Scene
		play_button.setOnAction(e -> Panel.setScene(scenes.get("Draw")));
		
		// Menu Bar
		bar = new MenuBar();
		Menu rules = new Menu("Rules of Keno");
		Menu odds = new Menu("Odds of Winning");
		Menu exit = new Menu("End Game");
		Menu theme = new Menu("Themes");
		
		MenuItem r = new MenuItem("See Rules");
		MenuItem o = new MenuItem("See Odds");
		MenuItem l = new MenuItem("Exit");
		
		MenuItem t1 = new MenuItem("Theme 1");
		MenuItem t2 = new MenuItem("Theme 2");
		MenuItem t3 = new MenuItem("Theme 3");
		
		rules.setStyle("-fx-font-family: SansSerif;");
		odds.setStyle("-fx-font-family: SansSerif;");
		exit.setStyle("-fx-font-family: SansSerif;");
		theme.setStyle("-fx-font-family: SansSerif;");
		
		r.setStyle("-fx-font-family: SansSerif;");
		o.setStyle("-fx-font-family: SansSerif;");
		l.setStyle("-fx-font-family: SansSerif;");
		t1.setStyle("-fx-font-family: SansSerif;");
		t2.setStyle("-fx-font-family: SansSerif;");
		t3.setStyle("-fx-font-family: SansSerif;");
		
		theme.setStyle("-fx-font-family: SansSerif;");
		
		
		r.setOnAction(e -> Panel.setScene(scenes.get("Rules")));
		rules.getItems().add(r);
		o.setOnAction(e -> Panel.setScene(scenes.get("OddW")));
		odds.getItems().add(o);
		l.setOnAction(endGame);
		exit.getItems().add(l);
		
		theme.getItems().add(t1);
		theme.getItems().add(t2);
		theme.getItems().add(t3);
		
		bar.getMenus().add(rules);
		bar.getMenus().add(odds);
		bar.getMenus().add(exit);
		bar.getMenus().add(theme);

		borderPane.setBottom(hBox_play);
		borderPane.setTop(bar);
		
    	Image default_image1 = new Image(theme_name);
		Background OriginalBG1 = new Background(new BackgroundImage(default_image1, BackgroundRepeat.NO_REPEAT, 	
																	BackgroundRepeat.NO_REPEAT,               	
																	BackgroundPosition.CENTER, null));                                           
		borderPane.setBackground(OriginalBG1);
		
	    t2.setOnAction(e-> {

	    	theme_name = "dark_theme.jpg";
	    	Image default_image = new Image(theme_name);
	    	Background OriginalBG = new Background(new BackgroundImage(default_image, BackgroundRepeat.NO_REPEAT, 	
	    																	BackgroundRepeat.NO_REPEAT,               
	    																	BackgroundPosition.CENTER, null));        
	        	
	    	borderPane.setBackground(OriginalBG);
	    });
	    
	    t3.setOnAction(e-> {
	    	theme_name = "bright_theme.jpg";
	    	Image default_image = new Image(theme_name);
	    	Background OriginalBG = new Background(new BackgroundImage(default_image, BackgroundRepeat.NO_REPEAT, 	
	    																	BackgroundRepeat.NO_REPEAT,       
	    																	BackgroundPosition.CENTER, null));        	
	    		borderPane.setBackground(OriginalBG);
	        });
	
		return new Scene(borderPane, 1000, 700);
	}
	// Game Rules Screen
	public Scene gameRules() {
		Button back = new Button("Back");
		Text t1 = new Text("TERMS");
		Text t2 = new Text("Bet Card: A grid of numbers (1-80) that the player uses to choose what numbers to play.");
		Text t3 = new Text("Number of Spots: A player can choose to play 1 number(1 Spot), 4 numbers(4 Spots), 8 numbers(8 Spots) or 10 numbers(10 Spots).");
		Text t4 = new Text("Drawings: Each unique selection of 20 random numbers, with no duplicates, by the game. Players may play a single bet card for a minimim of 1 and maximum of 4 drawings.");
		t1.setStyle("-fx-font-family: SansSerif;");
		t2.setStyle("-fx-font-family: SansSerif;");
		t3.setStyle("-fx-font-family: SansSerif;");
		t4.setStyle("-fx-font-family: SansSerif;");
		
		back.setOnAction(e -> Panel.setScene(scenes.get("Intro")));
		
		rootV = new VBox(10, t1, t2, t3, t4, back);
		rootV.setAlignment(Pos.CENTER);
		
		Image default_image1 = new Image(theme_name);
		Background OriginalBG1 = new Background(new BackgroundImage(default_image1, BackgroundRepeat.NO_REPEAT, 	
																	BackgroundRepeat.NO_REPEAT,               	
																	BackgroundPosition.CENTER, null));  
		rootV.setBackground(OriginalBG1);
		
		return new Scene(rootV, 1000, 700);
	}
	// Odds of Winning Screen
	public Scene winOdds() {
		Button back = new Button("Back");
		Text text = new Text("Odds of Winning\n" +
				"\n1 Spot: 1 in 4.00\n" +
				"\n4 Spots: 1 in 3.86\n" +
				"\n8 Spots: 1 in 9.77\n" +
				"\n10 Spots: 1 in 9.05\n");
		text.setStyle("-fx-font-family: SansSerif;");
		
		back.setOnAction(e -> Panel.setScene(scenes.get("Intro")));
		
		rootV = new VBox(10, text, back);
		rootV.setAlignment(Pos.CENTER);
		Image default_image1 = new Image(theme_name);
		Background OriginalBG1 = new Background(new BackgroundImage(default_image1, BackgroundRepeat.NO_REPEAT, 	
																	BackgroundRepeat.NO_REPEAT,               	
																	BackgroundPosition.CENTER, null));  
		rootV.setBackground(OriginalBG1);
		return new Scene(rootV, 1000, 700);
	}
	
	// # of Drawings Screen
	public Scene draw() {
		BorderPane pane = new BorderPane();
		Image default_image1 = new Image(theme_name);
		Background OriginalBG1 = new Background(new BackgroundImage(default_image1, BackgroundRepeat.NO_REPEAT, 	
																	BackgroundRepeat.NO_REPEAT,               	
																	BackgroundPosition.CENTER, null));  
		pane.setBackground(OriginalBG1);
		
		rootH = new HBox(10);
		rootV = new VBox(10);
		Button d1 = new Button("1");
		Button d2 = new Button("2");
		Button d3 = new Button("3");
		Button d4 = new Button("4");
		Button back = new Button("Back");
		
		d1.setStyle("-fx-font-family: SansSerif;");
		d2.setStyle("-fx-font-family: SansSerif;");
		d3.setStyle("-fx-font-family: SansSerif;");
		d4.setStyle("-fx-font-family: SansSerif;");
		
		Text text = new Text("       HOW MANY DRAWINGS\n" + "DO YOU WANT TO PLAY FOR?\n"
				+ "\n Click an option to continue:");
		text.setStyle("-fx-font-family: SansSerif;");
		
		rootH.getChildren().addAll(d1, d2, d3, d4);
		rootH.setAlignment(Pos.CENTER);
		rootH.setPadding(new Insets(0, 0, 200, 0));
		rootV.getChildren().addAll(text, rootH, back);
		rootV.setAlignment(Pos.CENTER);
		
		// Set drawNum variable
		d1.setOnAction(pickDraw);
		d2.setOnAction(pickDraw);
		d3.setOnAction(pickDraw);
		d4.setOnAction(pickDraw);
		back.setOnAction(e -> Panel.setScene(scenes.get("Intro")));
		
		pane.setCenter(rootV);
		
		return new Scene(pane, 1000, 700);
	}
	
	public Scene spots() {
		BorderPane pane = new BorderPane();
		rootH = new HBox(10);
		rootV = new VBox(10);
		Button back = new Button("Back");
		Button s1 = new Button("1");
		Button s4 = new Button("4");
		Button s8 = new Button("8");
		Button s10 = new Button("10");
		
		s1.setStyle("-fx-font-family: SansSerif;");
		s4.setStyle("-fx-font-family: SansSerif;");
		s8.setStyle("-fx-font-family: SansSerif;");
		s10.setStyle("-fx-font-family: SansSerif;");
		
		Text text = new Text("       HOW MANY SPOTS DO YOU\n" + "WANT TO PLAY?\n"
				+ "\n Click an option to continue:");
		text.setStyle("-fx-font-family: SansSerif;");
		
		rootH.getChildren().addAll(s1, s4, s8, s10);
		rootH.setAlignment(Pos.CENTER);
		rootH.setPadding(new Insets(0, 0, 200, 0));
		rootV.getChildren().addAll(text, rootH, back);
		rootV.setAlignment(Pos.CENTER);
		
		// Setting spotNum variable
		s1.setOnAction(pickSpot);
		s4.setOnAction(pickSpot);
		s8.setOnAction(pickSpot);
		s10.setOnAction(pickSpot);
		
		back.setOnAction(e -> Panel.setScene(scenes.get("Draw")));
		
		pane.setCenter(rootV);
		
		Image default_image1 = new Image(theme_name);
		Background OriginalBG1 = new Background(new BackgroundImage(default_image1, BackgroundRepeat.NO_REPEAT, 	
																	BackgroundRepeat.NO_REPEAT,               	
																	BackgroundPosition.CENTER, null));  
		pane.setBackground(OriginalBG1);
		
		return new Scene(pane, 1000, 700);
	}
	
	// Game Pause Screen
	public Scene pause() {
		BorderPane pane = new BorderPane();
		rootH = new HBox(10);
		rootV = new VBox(10);
		Button restart = new Button("Restart");
		Button cont = new Button("Continue");
		
		Text text = new Text("GAME PAUSED");
		text.setStyle("-fx-font-family: SansSerif;");
		
		restart.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				totalMatch = 0;
				currentMoney = 0;
				totalMoney = 0;
				userNum.clear();
				winNum.clear();
				System.out.println(winNum.size());
				drawNums = "";
				nums = "";
				mat = "";
				contG.setDisable(true);
				done.setDisable(true);
				Panel.setScene(scenes.get("Intro"));
				Panel.setScene(scenes.get("Intro"));
			}
		});
		cont.setOnAction(e -> Panel.setScene(scenes.get("Game")));
		
		rootH.getChildren().addAll(restart, cont);
		rootV.getChildren().addAll(text, rootH);
		rootH.setPadding(new Insets(0, 0, 200, 0));
		rootH.setAlignment(Pos.CENTER);
		rootV.setAlignment(Pos.CENTER);
		
		pane.setCenter(rootV);
		Image default_image1 = new Image(theme_name);
		Background OriginalBG1 = new Background(new BackgroundImage(default_image1, BackgroundRepeat.NO_REPEAT, 	
																	BackgroundRepeat.NO_REPEAT,               	
																	BackgroundPosition.CENTER, null));  
		pane.setBackground(OriginalBG1);
		return new Scene(pane, 1000, 700);
	}
	
	// Main Game Screen
	public Scene game() {
		grid = new GridPane();
		bar = new MenuBar();
		Menu rules = new Menu("Rules of Keno");
		Menu odds = new Menu("Odds of Winning");
		Menu theme = new Menu("Themes");
		Menu exit = new Menu("End Game");
		MenuItem r = new MenuItem("See Rules");
		MenuItem o = new MenuItem("See Odds");
		
		MenuItem t1 = new MenuItem("Theme 1");
		MenuItem t2 = new MenuItem("Theme 2");
		MenuItem t3 = new MenuItem("Theme 3");
		
		MenuItem l = new MenuItem("Exit");
		
		rules.setStyle("-fx-font-family: SansSerif;");
		odds.setStyle("-fx-font-family: SansSerif;");
		theme.setStyle("-fx-font-family: SansSerif;");
		exit.setStyle("-fx-font-family: SansSerif;");
		r.setStyle("-fx-font-family: SansSerif;");
		o.setStyle("-fx-font-family: SansSerif;");
		t1.setStyle("-fx-font-family: SansSerif;");
		t2.setStyle("-fx-font-family: SansSerif;");
		t3.setStyle("-fx-font-family: SansSerif;");
		l.setStyle("-fx-font-family: SansSerif;");
	

		r.setOnAction(e -> Panel.setScene(scenes.get("Rules")));
		rules.getItems().add(r);
		o.setOnAction(e -> Panel.setScene(scenes.get("OddW")));
		odds.getItems().add(o);
		theme.getItems().add(t1);
		theme.getItems().add(t2);
		theme.getItems().add(t3);
		l.setOnAction(endGame);
		exit.getItems().add(l);
		
		bar.getMenus().add(rules);
		bar.getMenus().add(odds);
		bar.getMenus().add(theme);
		bar.getMenus().add(exit);
		
		Button pause = new Button("Pause");
		Button reset = new Button("Reset");
		Button random = new Button("Random Picker");
		
		makeGrid(grid);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setAlignment(Pos.CENTER_LEFT);
		
		pause.setOnAction(e -> Panel.setScene(scenes.get("Paused")));
		contG.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				
				Text t1 = new Text();
				Text t2 = new Text();
				Text t3 = new Text();
				Text t4 = new Text();
				
				DrawNums.setStyle("-fx-font-family: SansSerif;");
				t2.setStyle("-fx-font-family: SansSerif;");
				t3.setStyle("-fx-font-family: SansSerif;");
				t4.setStyle("-fx-font-family: SansSerif;");
				
				nums += "Your Numbers: ";
				for (int i=0; i < spotNum; i++) {
					nums += userNum.get(i) + " ";
				}
				Nums.setText(nums);
				
				for (int i=0; i < drawNum; i++) {
					drawNums += "Drawing " + (i+1) + ": ";
					if (i == 0) {
						for (int j=0; j < 20; j++) {
							drawNums += winNum.get(j) + " ";
						}
					}
					if (i == 1) {
						for (int j=20; j < 40; j++) {
							drawNums += winNum.get(j) + " ";
						}
					}
					if (i == 2) {
						for (int j=40; j < 60; j++) {
							drawNums += winNum.get(j) + " ";
						}
					}
					if (i == 3) {
						for (int j=60; j < 80; j++) {
							drawNums += winNum.get(j) + " ";
						}
					}
					drawNums += "\n";
				}
				
				DrawNums.setText(drawNums);
	
				
				mat += "Matches:\n";
				for (int k=0; k < drawNum; k++) {
					mat += "Draw " + (k+1) + ": ";
					for (int i=0; i < userNum.size(); i++) {
						for (int j=0; j < winNum.size() / 2; j++) {
							if (userNum.get(i) == winNum.get(j)) {
								mat += userNum.get(i) + " ";
								tempMatch++;
								totalMatch++;
							}
						}
					}
					if (tempMatch == 0) {
						mat += "N/A";
					}
					mat += "\n";
					
					if (spotNum == 1) {
						if (tempMatch == 1) {
							currentMoney += 2;
						}
					}
					
					if (spotNum == 4) {
						if (tempMatch == 4) {
							currentMoney += 75;
						}
						if (tempMatch == 3) {
							currentMoney += 27;
						}
						if (tempMatch == 2) {
							currentMoney += 1;
						}
					}

					if (spotNum == 8) {
						if (tempMatch == 8) {
							currentMoney += 10000;
						}
						if (tempMatch == 7) {
							currentMoney += 750;
						}
						if (tempMatch == 6) {
							currentMoney += 50;
						}
						if (tempMatch == 5) {
							currentMoney += 12;
						}
						if (tempMatch == 4) {
							currentMoney += 2;
						}
					}
					
					if (spotNum == 10) {
						if (tempMatch == 10) {
							currentMoney += 100000;
						}
						if (tempMatch == 9) {
							currentMoney += 4250;
						}
						if (tempMatch == 8) {
							currentMoney += 450;
						}
						if (tempMatch == 7) {
							currentMoney += 40;
						}
						if (tempMatch == 6) {
							currentMoney += 15;
						}
						if (tempMatch == 5) {
							currentMoney += 2;
						}
						if (tempMatch == 0) {
							currentMoney += 5;
						}
					}
					rw += "";
					totalMoney += currentMoney;
					currentMoney = 0;
					tempMatch = 0;
				}
				String mat2 = "Matches\n";
				int zero = 1;
				int one = 1;
				int two = 1;
				int three = 1;
				
				for (int i = 0; i < spotNum; i++) {
					// check if num pick in win numbers
					for (int j = 0; j < winNum.size(); j++) {
						if (userNum.get(i) == winNum.get(j)) {
							
							if (j >= 0 && zero==1) {
								mat2 += "Drawing 1: ";
								zero = 0;
							}
							
							
							if (j >= 20 && one==1) {
								mat2 += "\n";
								mat2 += "Drawing 2: ";
								one = 0;
							}
							
							if(j >= 40 && two==1) {
								mat2 += "\n";
								mat2 += "Drawing 3: ";
								two = 0;
							}
							if(j >= 60 && three == 1) {
								mat2 += "\n";
								mat2 += "Drawing 4: ";
								three = 0;
							}
							
							mat2 += " " + String.valueOf(winNum.get(j));
							j = winNum.size();
						}
						
						
						
					}
				}
				
				
				matches.setText(mat2);
				matches.setStyle("-fx-font-family: SansSerif;");
//				System.out.println(mat);
				
				rm += "Total Matches: " + totalMatch;
				rt += "Total Winnings: $" + totalMoney;
				
				rM.setText(rm);
				rW.setText(rw);
				rT.setText(rt);
				
				rW.setStyle("-fx-font-family: SansSerif;");
				rT.setStyle("-fx-font-family: SansSerif;");
				rM.setStyle("-fx-font-family: SansSerif;");
				
				Panel.setScene(scenes.get("Results"));
			}
		});
		contG.setDisable(true);
		reset.setOnAction(restart);
		random.setOnAction(randPick);
		
		VBox leftSide = new VBox(10, pickText, reset, random, contG);
		leftSide.setPadding(new Insets(100));
		rootH = new HBox(grid, leftSide);
		rootH.setPadding(new Insets(100));
		rootV = new VBox(10, bar, rootH, pause);
		rootV.setAlignment(Pos.TOP_CENTER);
		
    	Image default_image1 = new Image(theme_name);
		Background OriginalBG1 = new Background(new BackgroundImage(default_image1, BackgroundRepeat.NO_REPEAT, 	
																	BackgroundRepeat.NO_REPEAT,               	
																	BackgroundPosition.CENTER, null));                                           
		rootV.setBackground(OriginalBG1);

        t1.setOnAction(e-> {
        	theme_name = "default.png";
        	Image default_image = new Image(theme_name);
    		Background OriginalBG = new Background(new BackgroundImage(default_image, BackgroundRepeat.NO_REPEAT, 	
    																	BackgroundRepeat.NO_REPEAT,               	
    																	BackgroundPosition.CENTER, null));                                           
        	contG.setBackground(OriginalBG);
            rootV.setBackground(OriginalBG);
            grid.setBackground(OriginalBG);
        });
        
        t2.setOnAction(e-> {
        	theme_name = "dark_theme.jpg";
        	Image default_image = new Image(theme_name);
    		Background OriginalBG = new Background(new BackgroundImage(default_image, BackgroundRepeat.NO_REPEAT, 	
    																	BackgroundRepeat.NO_REPEAT,               
    																	BackgroundPosition.CENTER, null));        
        	
        	contG.setBackground(OriginalBG);
            rootV.setBackground(OriginalBG);
            grid.setBackground(OriginalBG);
        });
        
        t3.setOnAction(e-> {
        	theme_name = "bright_theme.jpg";
        	Image default_image = new Image(theme_name);
    		Background OriginalBG = new Background(new BackgroundImage(default_image, BackgroundRepeat.NO_REPEAT, 	
    																	BackgroundRepeat.NO_REPEAT,       
    																	BackgroundPosition.CENTER, null));        	
        	contG.setBackground(OriginalBG);
            rootV.setBackground(OriginalBG);
            grid.setBackground(OriginalBG);
        });
		return new Scene(rootV, 1000, 700);
	}
	
	// Current/Overall Game Results
	public Scene results() {
		Text curr = new Text(" ");
		Text oa = new Text("- Overall -");
		
		curr.setStyle("-fx-font-family: SansSerif;");
		
		oa.setStyle("-fx-font-family: SansSerif;");
		
		contR.setOnAction(e -> Panel.setScene(scenes.get("Fin")));
		
		VBox left = new VBox(10, curr, rW);
		VBox right = new VBox(10, oa, rM, rT);
		rootH = new HBox(10, left, right);
		rootH.setAlignment(Pos.CENTER);
		rootV = new VBox(10, Nums, DrawNums, matches, rootH, contR);
		rootV.setAlignment(Pos.CENTER);
		Image default_image1 = new Image(theme_name);
		Background OriginalBG1 = new Background(new BackgroundImage(default_image1, BackgroundRepeat.NO_REPEAT, 	
																	BackgroundRepeat.NO_REPEAT,               	
																	BackgroundPosition.CENTER, null));  
		rootV.setBackground(OriginalBG1);
		return new Scene(rootV, 1000, 700);
	}
	
	// 'Final' Screen
	public Scene fin() {
		Button again = new Button("Play Again");
		Button exit = new Button("Exit Game");
		Text text = new Text("Play Again?");
		
		again.setOnAction(restart);
		exit.setOnAction(endGame);
		
		rootH = new HBox(10, again, exit);
		rootH.setAlignment(Pos.CENTER);
		rootV = new VBox(10, text, rootH);
		rootV.setAlignment(Pos.CENTER);
		Image default_image1 = new Image(theme_name);
		Background OriginalBG1 = new Background(new BackgroundImage(default_image1, BackgroundRepeat.NO_REPEAT, 	
																	BackgroundRepeat.NO_REPEAT,               	
																	BackgroundPosition.CENTER, null));  
		rootV.setBackground(OriginalBG1);
		return new Scene(rootV, 1000, 700);
	}
	
	// Creates grid and buttons
	public void makeGrid(GridPane grid) {
		int num = 1;
		for (int i=0 ; i < 8; i++) {
			for (int j=0; j < 10; j++) {
				Button b = new Button(String.valueOf(num));
				b.setOnAction(select);
				b.setStyle(def);
				arrB[i][j] = b;
				grid.add(arrB[i][j], j, i);
				num++;
			}
		}
	}
	
	EventHandler<ActionEvent> pickDraw = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			Button b = (Button)e.getSource();
			String ss = b.getText();
			if (ss == "1") {
				drawNum = 1;
				drawTemp = drawNum;
			}
			if (ss == "2") {
				drawNum = 2;
				drawTemp = drawNum;
			}
			if (ss == "3") {
				drawNum = 3;
				drawTemp = drawNum;
			}
			if (ss == "4") {
				drawNum = 4;
				drawTemp = drawNum;
			}
			
			Random random = new Random(System.currentTimeMillis());
			for (int i=0; i < drawNum; i++) {
				for (int j=0; j < 20; j++) {
					// Getting random number
					int num = random.nextInt(80);
					if (num == 0) {
						num++;
					}
					
					// Checking for duplicates
					int k =0;
					while(k < winNum.size()) {
						if (num == winNum.get(k)) {
							num = random.nextInt(80);
							k = 0;
						}
						else {
							k++;
						}
					}
					// Adding to array
					winNum.add(num);
				}
			}
			Panel.setScene(scenes.get("Spots"));
		}
	};
	
	EventHandler<ActionEvent> pickSpot = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			Button b = (Button)e.getSource();
			String ss = b.getText();
			if (ss == "1") {
				spotNum = 1;
				spotTemp = spotNum;
			}
			if (ss == "4") {
				spotNum = 4;
				spotTemp = spotNum;
			}
			if (ss == "8") {
				spotNum = 8;
				spotTemp = spotNum;
			}
			if (ss == "10") {
				spotNum = 10;
				spotTemp = spotNum;
			}
			pickText.setText("Pick " + spotNum + " Numbers: ");
			pickText.setStyle("-fx-font-family: SansSerif;");
			Panel.setScene(scenes.get("Game"));
		}
	};
	
	EventHandler<ActionEvent> select = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			Button b = (Button)e.getSource();
			b.setStyle(picked);
			b.setDisable(true);
			int num = Integer.parseInt(b.getText());
			userNum.add(num);
			if (spotTemp > 0) {
				spotTemp--;
			}
			if (spotTemp == 0) {
				contG.setDisable(false);
				for (int i=0 ; i < 8; i++) {
					for (int j=0; j < 10; j++) {
						arrB[i][j].setDisable(true);
					}
				}
			}
		}
	};
	
	EventHandler<ActionEvent> randPick = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			Button b = (Button)e.getSource();
			b.setDisable(true);
			
			for (int i=spotTemp; i > 0; i--) {
				int num = (int)(Math.random() * 80 + 1);
				userNum.add(num);
				spotTemp--;
			}
			
			if (spotTemp == 0) {
				contG.setDisable(false);
				for (int i=0 ; i < 8; i++) {
					for (int j=0; j < 10; j++) {
						arrB[i][j].setDisable(true);
					}
				}
			}
		}
	};
	
	EventHandler<ActionEvent> restart = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			Button b = (Button)e.getSource();
			String ss = b.getText();
			for (int i=0 ; i < 8; i++) {
				for (int j=0; j < 10; j++) {
					arrB[i][j].setStyle(def);
					arrB[i][j].setDisable(false);
				}
			}
			
			if (ss == "Reset") {
				contG.setDisable(true);
				userNum.clear();
				spotTemp = spotNum;
			}
			if (ss == "Next") {
				if (drawNum == 1) {
					done.setDisable(false);
				}
				drawNum--;
				spotTemp = spotNum;
				contG.setDisable(true);
				Panel.setScene(scenes.get("Results"));
			}
			if (ss == "Play Again") {
				totalMatch = 0;
				currentMoney = 0;
				totalMoney = 0;
				userNum.clear();
				winNum.clear();
				drawNums = "";
				nums = "";
				mat = "";
				rm = "";
				rw = "";
				rt = "";
				contG.setDisable(true);
				done.setDisable(true);
				Panel.setScene(scenes.get("Intro"));
			}
		}

	};
	
	// End Game Action
	EventHandler<ActionEvent> endGame = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			Platform.exit();
		}
	};
	
}
