package am.data.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Routestop {

	private Long routestop_id;
	@NonNull
	private Long route_id;
	@NonNull
	private Long order_id;
	@NonNull
	private Integer stop_number;
}
