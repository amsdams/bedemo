import { browser, by, element } from 'protractor';

export class JaarlijksePremieStortingBerekeningFormPage {
  navigateTo() {
    return browser.get(browser.baseUrl+'/maak-jaarlijksepremiestortingberekening') as Promise<any>;
  }

  getTitleText() {
    return element(by.css('app-root h2')).getText() as Promise<string>;
  }
/*voltijdSalaris?: number;
	deeltijdPercentage?: number;
	voltijdFranchise?: number;
    premiePercentage?: number;
    jaarlijksePremieStortingResultaat?: number;
    */

  getVoltijdSalarisInput() {
    return element(by.css('input[id=voltijdSalaris]'));
  }

  getDeeltijdPercentageInput() {
    return element(by.css('input[id=deeltijdPercentage]'));
  }

  getVoltijdFranchiseInput() {
    return element(by.css('input[id=voltijdFranchise]'));
  }

  getPremiePercentageInput() {
    return element(by.css('input[id=premiePercentage]'));
  }

  getJaarlijksePremieStortingResultaatInput() {
    return element(by.css('input[id=jaarlijksePremieStortingResultaat]'));
  }

  getBerekenButton() {
    return element(by.cssContainingText('button', 'Bereken'));
}
}