package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

/**
 * Created by tomas on 2/11/16.
 */
public class ImageDto{

	private ImageTypeEnum type;
	private String content;

	public ImageDto() {
	}

	public ImageDto(ImageTypeEnum type, String content){
		this.type = type;
		this.content = content;
	}

	public ImageTypeEnum getType(){
		return type;
	}

	public String getContent(){
		return content;
	}
	public void setType(ImageTypeEnum type) {
		this.type = type;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
