#include "Mapa.h"

Mapa::Mapa() {
    //Suroviny პლანეტაზე რაც გვაქვს
    m_suroviny.push_back(new Suroviny("Gold"));p+=1;
    m_suroviny.push_back(new Suroviny("Silver"));p+=1;
    m_suroviny.push_back(new Suroviny("Almaz"));p+=1;

    //Planety  ტავიანთი ყიდვა-გაყიდვის ფასებით
    m_planety.push_back(new Planeta("Zem",20,30, new Pirat(new Zbran(50),new SilnaObrana(50,5),10)));p+=1;
    m_planety.at(0)->pridejSurovinu(m_suroviny.at(0));p+=1;
    m_planety.at(0)->pridejSurovinu(m_suroviny.at(2));p+=1;

    m_planety.push_back(new Planeta("Mars",10,20, new Pirat(new Zbran(50),new SlabaObrana(50,5),10)));p+=1;
    m_planety.at(1)->pridejSurovinu(m_suroviny.at(0));p+=1;
    m_planety.at(1)->pridejSurovinu(m_suroviny.at(2));p+=1;
    m_planety.at(1)->pridejSurovinu(m_suroviny.at(1));p+=1;

    m_planety.push_back(new Planeta("Pluton",15,25, new Pirat(new Zbran(50),new SilnaObrana(50,5),10)));p+=1;
    m_planety.at(2)->pridejSurovinu(m_suroviny.at(2));p+=1;
}

void Mapa::menu(Lod* lod) {
    int i,ktera;

    do {
    cout<<">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"<<endl
        <<"     Planety ve hre: "<<endl;
        for(i=0; i<m_planety.size(); i++){
            cout<<"   "<<i<<"  "<<getPlaneta(i)->getNazev()<<endl;
        }
        cout<<endl<<"   "<<i<<" -> Konec "<<endl;
        cout<<"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"<<endl;
        cout<<endl<<"Vyber planetu : ";  cin>>ktera;

        if(ktera < i) {
            getPlaneta(ktera)->menu(lod);
        }

    } while((ktera<0)||(ktera < i));
}

Planeta* Mapa::getPlaneta(int ktera) {
    return m_planety.at(ktera);
}
