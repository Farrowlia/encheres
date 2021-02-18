package org.eni.encheres.bo;

public class Image {
	
	private int noImage;
	private String cheminUrl;
	private ArticleVendu articleVendu;
	
	
	public Image() {
	}
	public Image(int noImage, String cheminUrl, ArticleVendu articleVendu) {
		this.noImage = noImage;
		this.cheminUrl = cheminUrl;
		this.articleVendu = articleVendu;
	}


	public int getNoImage() {
		return noImage;
	}
	public void setNoImage(int noImage) {
		this.noImage = noImage;
	}

	
	public String getCheminUrl() {
		return cheminUrl;
	}
	public void setCheminUrl(String cheminUrl) {
		this.cheminUrl = cheminUrl;
	}

	
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

}
