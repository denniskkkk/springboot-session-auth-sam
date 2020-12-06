package dbx.zzzz.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter
public class Msg {
	
	private Long idx;
	@NonNull
	private String msg;
	
}

