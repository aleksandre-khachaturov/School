#include "Inventar.h"

Inventar::Inventar() {
    m_velikost = 10;
}

int Inventar::getVelikost() {
	return m_velikost;
}

Suroviny* Inventar::getSurovina(int kolikata) {
	return m_suroviny.at(kolikata);
}

int Inventar::getPocetSurovin() {
	return m_suroviny.size();
}

void Inventar::pridejSurovinu(Suroviny* surovina) {
	m_suroviny.push_back(surovina);
	m_velikost -= 1;
}

void Inventar::odeberSurovinu(int kolikata) {
	m_suroviny.erase(m_suroviny.begin()+kolikata);
	m_velikost += 1;
}

void Inventar::printInfo() {
    if (getPocetSurovin()==0){
        cout<<"Prazdny inventar"<<endl;
    } else {
        for (int i=0; i<getPocetSurovin(); i++)
        {
            cout<<"("<<i<<")";
            getSurovina(i)->printInfo();
            cout<<endl;
        }
        cout<<"-----------------------------"<<
        endl<<"        Celkem surovin: "<<getPocetSurovin()<<endl;
    }
}
