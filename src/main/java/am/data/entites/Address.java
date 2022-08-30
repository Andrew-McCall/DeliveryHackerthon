package am.data.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Address {

	private Long address_id;
	@NonNull
	private String address_line_one;
	@NonNull
	private String address_line_two;
	@NonNull
	private String city;
	@NonNull
	private String postcode;

}
