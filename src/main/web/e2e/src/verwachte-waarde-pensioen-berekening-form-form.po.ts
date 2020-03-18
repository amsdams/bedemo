import { browser, by, element } from 'protractor';

export class VerwachteWaardePensioenBerekeningFormPage {
  navigateTo() {
    return browser.get(browser.baseUrl+'/maak-verwachtewaardepensioenberekening') as Promise<any>;
  }

  getTitleText() {
    return element(by.css('app-root h2')).getText() as Promise<string>;
  }
/*
huidigeLeeftijdDeelnemer?: number;
	gewenstePensioenLeeftijdDeelnemer?: number;
	
	huidigeWaardeBeleggingen?: number;
	jaarlijksePremieStorting?: number;
	jaarlijksRendementBeleggingen?: number;
	
  verwachteWaardePensioenResultaat?: number;
  
    */

  getHuidigeLeeftijdDeelnemerInput() {
    return element(by.css('input[id=huidigeLeeftijdDeelnemer]'));
  }

  getGewenstePensioenLeeftijdDeelnemerInput() {
    return element(by.css('input[id=gewenstePensioenLeeftijdDeelnemer]'));
  }

  getHuidigeWaardeBeleggingenInput() {
    return element(by.css('input[id=huidigeWaardeBeleggingen]'));
  }

  getJaarlijksePremieStortingInput() {
    return element(by.css('input[id=jaarlijksePremieStorting]'));
  }

  getJaarlijksRendementBeleggingenInput() {
    return element(by.css('input[id=jaarlijksRendementBeleggingen]'));
  }

  getBerekenButton() {
    return element(by.cssContainingText('button', 'Bereken'));
}
}