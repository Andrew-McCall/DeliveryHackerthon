package am.data.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Route {

	public Route(@NonNull Long login_id) {
		this.login_id = login_id;
		this.distance = 0;
		this.stop_count = 0;
	}

	private Long route_id;
	@NonNull
	private Integer distance; // 0 Default (Calculated)
	@NonNull
	private Integer stop_count; // 0 Default (Calculated)
	@NonNull
	private Long login_id;

}
