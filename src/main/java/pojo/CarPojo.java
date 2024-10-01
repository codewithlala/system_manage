package pojo;

public class CarPojo {
	private int carID;
	private String carModel;
	private String carBrand;
	private double carPrice;
	private int carYear;

	// Default constructor
	public CarPojo() {
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	// Parameterized constructor
	public CarPojo(int carId, String carModel, String carBrand, double carPrice, int carYear) {
		this.carID = carId;
		this.carModel = carModel;
		this.carBrand = carBrand;
		this.carPrice = carPrice;
		this.carYear = carYear;
	}

	// Getters and setters

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}

	public int getCarYear() {
		return carYear;
	}

	public void setCarYear(int carYear) {
		this.carYear = carYear;
	}

	@Override
	public String toString() {
		return "InsertCarPojo [carModel=" + carModel + ", carBrand=" + carBrand + ", carPrice=" + carPrice
				+ ", carYear=" + carYear + "]";
	}

}
