package com.inventory.responseVM;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UnitResponse {
	@NonNull
	private Integer id;
	@NonNull
	private String name;
	@NonNull
	private String unitDescription;
	@NonNull
	private Boolean delStatus;
}

//lenh cai dat lombok
//1. cai moi truong Java Runtime Environment
//2. cai dat lombok
//java -jar C:\Users\nguyentha\Downloads\lombok.jar
