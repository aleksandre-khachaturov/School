#include "Lod.h"

Lod::Lod(string jmeno, Obrana* obrana) {
	m_jmeno = jmeno;
	m_obrana = obrana;
	m_penize = 1000;
	m_zbran = new Zbran(100);
	m_inventar = new Inventar();
	m_points = 0;
}

string Lod::getJmeno() {
	return m_jmeno;
}

int Lod::getPenize() {
	return m_penize;
}

Inventar* Lod::getInventar() {
	return m_inventar;
}

Zbran* Lod::getZbran() {
	return m_zbran;
}

Obrana* Lod::getObrana() {
	return m_obrana;
}

int Lod::getPoints() {
	return m_points;
}

void Lod::pridejPoints() {
	m_points += 5;
}

void Lod::pridejPenize(int kolik) {
	m_penize += kolik;
}

void Lod::odeberPenize(int kolik) {
	m_penize -= kolik;
}

void Lod::printInfo() {
    cout<<endl<<"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"<<endl<<endl;
    cout<<"Informace o sobe : "<<endl;
	cout<<"                   Jmeno: "<<m_jmeno<<endl;
	cout<<"                   Penize: "<<m_penize<<endl;
	cout<<"                   Points: "<<m_points<<endl;
	cout<<"                   ";  getZbran()->printInfo(); cout<<endl;
	cout<<"                   ";  getObrana()->printInfo(); cout<<endl;
	cout<<"";  getInventar()->printInfo(); cout<<endl;
	cout<<"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"<<endl;
}
