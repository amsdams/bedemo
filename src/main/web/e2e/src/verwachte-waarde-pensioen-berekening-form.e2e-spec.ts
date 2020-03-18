
import { browser, logging } from 'protractor';
import { VerwachteWaardePensioenBerekeningListPage } from './verwachte-waarde-pensioen-berekening-form-list.po';
import { VerwachteWaardePensioenBerekeningFormPage } from './verwachte-waarde-pensioen-berekening-form-form.po';

describe('workspace-project VerwachteWaardePensioenBerekeningFormPage', () => {
  let page: VerwachteWaardePensioenBerekeningFormPage;
  let pageResult: VerwachteWaardePensioenBerekeningListPage;
  const timeout = 1000;

  beforeEach(() => {
    page = new VerwachteWaardePensioenBerekeningFormPage();
    pageResult = new VerwachteWaardePensioenBerekeningListPage();
  });

  it('should display BeDemo', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('BeDemo');
  });

  it('Should berekenen #1', () => {
    page.navigateTo();

    page.getHuidigeLeeftijdDeelnemerInput().sendKeys('35');
    page.getGewenstePensioenLeeftijdDeelnemerInput().sendKeys('68');
    page.getHuidigeWaardeBeleggingenInput().sendKeys('100.00');
    page.getJaarlijksePremieStortingInput().sendKeys('434.38255000000004');
    page.getJaarlijksRendementBeleggingenInput().sendKeys('3.00');
    page.getBerekenButton().click();

    browser.driver.wait(function () {
      return browser.driver.getCurrentUrl().then(function (url) {
        return (/toon-alle-verwachtewaardepensioenberekeningen/).test(url);
      });
    }, timeout).then(function () {
      return;
    });
    expect(browser.getCurrentUrl()).toContain('/toon-alle-verwachtewaardepensioenberekeningen');


    //pageResult.navigateTo();

    expect(pageResult.getLastRowCell()).toEqual('24548.959464041167');
  });

  it('Should berekenen #2', () => {
    page.navigateTo();

    page.getHuidigeLeeftijdDeelnemerInput().sendKeys('35');
    page.getGewenstePensioenLeeftijdDeelnemerInput().sendKeys('68');
    page.getHuidigeWaardeBeleggingenInput().sendKeys('100.00');
    page.getJaarlijksePremieStortingInput().sendKeys('1220.05');
    page.getJaarlijksRendementBeleggingenInput().sendKeys('3.00');
    page.getBerekenButton().click();

    browser.driver.wait(function () {
      return browser.driver.getCurrentUrl().then(function (url) {
        return (/toon-alle-verwachtewaardepensioenberekeningen/).test(url);
      });
    }, timeout).then(function () {
      return;
    });
    expect(browser.getCurrentUrl()).toContain('/toon-alle-verwachtewaardepensioenberekeningen');


    //pageResult.navigateTo();

    expect(pageResult.getLastRowCell()).toEqual('68470.91957947443');
  });



  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});