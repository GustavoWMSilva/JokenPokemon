package poo.modelo;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageFactory {
	private static ImageFactory imgf = new ImageFactory();
	private Map<String, Image> images;

	public static ImageFactory getInstance() {
		return imgf;
	}

	private ImageFactory() {
		images = new HashMap<>();
	}

	private String id2File(String imgId) {
		switch (imgId) {
			case "img10":
				return ("/imagens/bulbasaur.png");
			case "img11":
				return ("/imagens/ivysaur.png");
			case "img12":
				return ("/imagens/venusaur.png");
			case "img20":
				return ("/imagens/charmander.png");
			case "img21":
				return ("/imagens/charmeleon.png");
			case "img22":
				return ("/imagens/charizard.png");
			case "img30":
				return ("/imagens/squirtle.png");
			case "img31":
				return ("/imagens/wartortle.png");
			case "img32":
				return ("/imagens/blastoise.png");
			case "img40":
				return ("/imagens/ditto.png");
			case "img50":
				return ("/imagens/snorlax1.png");
			case "img51":
				return ("/imagens/snorlax2.png");
			case "imgBck":
				return ("/imagens/back.png");
			default:
				throw new IllegalArgumentException("Invalid image Id");
			}
	}


	public ImageView createImage(String imgId) {
		Image img = images.get(imgId);
		
		if (img == null) {
//			img = new Image(id2File(imgId));
			img = new Image(getClass().getResourceAsStream(id2File(imgId)),300,150,true,true);
			images.put(imgId, img);
		}

		ImageView imgv = new ImageView(img);
		return imgv;
	}
}
