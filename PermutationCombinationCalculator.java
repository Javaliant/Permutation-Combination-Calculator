/* Author: Luigi Vincent
Simple Permutation & Combination Calculator

Made for CodeReview's April Community Challenge

Part of a series of simple apps made for the sake of learning
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PermutationCombinationCalculator extends Application {
	static final String ERROR = "Invalid input";

	@Override
	public void start(Stage stage) {
		TextField entry = new TextField();
		entry.setPromptText("Enter a value to permutate/combine here");

		TextField limiter = new TextField();
		limiter.setPromptText("Enter S, where S is the number of selections possible");
		
		Label permutationResult = new Label("Result will be here!");
		Label combinationResult = new Label("Result will be here!");

		Button permutate = new Button("Permutate");
		permutate.setOnAction(e -> {
			permutationResult.setText(
				computePermutation(entry.getText(), limiter.getText())
			);
		});

		Button combine = new Button("Combine");
		combine.setOnAction(e -> {
			combinationResult.setText(
				computeCombination(entry.getText(), limiter.getText())
			);
		});

		HBox permutationLayout = new HBox();
		permutationLayout.getChildren().addAll(permutate, permutationResult);

		HBox combinationLayout = new HBox();
		combinationLayout.getChildren().addAll(combine, combinationResult);

		VBox layout = new VBox();
		layout.getChildren().addAll(entry, limiter, permutationLayout, combinationLayout);

		stage.setScene(new Scene(layout));
		stage.show();
	}

	private static String computePermutation(String entry, String limit) {
		int total;
		int select;

		try {
			total = Integer.parseInt(entry);
			select = Integer.parseInt(limit);
		} catch(NumberFormatException nfe) {
			return ERROR;
		}

		if (limit.isEmpty()) {
			return Long.toString(permutate(total));
		}
		return Long.toString(permutate(total, select));
	}

	private static String computeCombination(String entry, String limit) {
		int total;
		int select;
		
		try {
			total = Integer.parseInt(entry);
			select = Integer.parseInt(limit);
		} catch(NumberFormatException nfe) {
			return ERROR;
		}
		return Long.toString(combine(total, select));
	}

  	private static long permutate(int p, int r) {
		long result = 1L;
		for (int i = p; r > 0; r--, p--) {
			result *= p;
		}

		return result;
	}

	private static long permutate(int p) {
		return permutate(p, p);
	}

	private static long combine(int total, int select) {
		select = Math.min(select, total - select);
    	return permutate(total, select) / permutate(select);
	}

	public static void main(String[] args) {
		launch(args);
	}
}