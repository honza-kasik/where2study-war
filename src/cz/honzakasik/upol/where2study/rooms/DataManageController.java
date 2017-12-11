package cz.honzakasik.upol.where2study.rooms;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.bind.JAXBException;

import cz.honzakasik.upol.where2study.datamanage.DataDownloader;
import cz.honzakasik.upol.where2study.datamanage.DataPurge;

@RequestScoped
@ManagedBean
public class DataManageController {
	
	@EJB
	private DataDownloader dataDownloader;
	
	@EJB
	private DataPurge dataPurge;

	public void downloadAllData() throws JAXBException, IllegalStateException, IOException {
		dataDownloader.downloadData();
	}
	
	
	public void removeAllData() {
		dataPurge.purgeAllPersistedData();
	}
	
}
