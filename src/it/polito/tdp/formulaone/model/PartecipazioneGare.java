package it.polito.tdp.formulaone.model;

public class PartecipazioneGare {
	
	private Race r1;
	private Race r2;
	private double peso;
	public PartecipazioneGare(Race r1, Race r2, double peso) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.peso = peso;
	}
	public Race getR1() {
		return r1;
	}
	public void setR1(Race r1) {
		this.r1 = r1;
	}
	public Race getR2() {
		return r2;
	}
	public void setR2(Race r2) {
		this.r2 = r2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((r1 == null) ? 0 : r1.hashCode());
		result = prime * result + ((r2 == null) ? 0 : r2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartecipazioneGare other = (PartecipazioneGare) obj;
		if (r1 == null) {
			if (other.r1 != null)
				return false;
		} else if (!r1.equals(other.r1))
			return false;
		if (r2 == null) {
			if (other.r2 != null)
				return false;
		} else if (!r2.equals(other.r2))
			return false;
		return true;
	}

}
