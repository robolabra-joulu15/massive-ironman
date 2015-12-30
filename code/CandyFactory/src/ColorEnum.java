
public enum ColorEnum {
	PUNAINEN(0), VIHREA(1), SININEN(2), KELTAINEN(3), VALKOINEN(6), MUSTA(7), EIMIKAAN(-1);

	private int id;

	private ColorEnum(int id) {
		this.id = id;
	}

	public static ColorEnum getInstance(int id) {
		for (ColorEnum colorId : ColorEnum.values()) {
			if (colorId.id == id) {
				return colorId;
			}
		}
		return EIMIKAAN;
	}



}
