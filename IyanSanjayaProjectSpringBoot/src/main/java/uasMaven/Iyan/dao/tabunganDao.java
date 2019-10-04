package uasMaven.Iyan.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasMaven.Iyan.model.tabunganModel;
import uasMaven.Iyan.repository.tabunganRepository;



@Service
public class tabunganDao {
	@Autowired
	tabunganRepository tabunganrepository;
	
	public List<tabunganModel> findAll(){
		return tabunganrepository.findAll();
		
	}
	
	public tabunganModel getFindOneBy_id(Long id) {
		return tabunganrepository.findOne(id);
		
	}
	public tabunganModel saveTabungan(tabunganModel tabungan) {
		tabunganModel data_tabungan=tabungan;
		tabunganModel tabungan_saldo=tabunganrepository.getSaldoByNik(tabungan.getNik());
		if(tabungan_saldo!=null) {
			data_tabungan.setSaldo(tabungan_saldo.getSaldo()+(tabungan.getKredit()-tabungan.getDebet()));
			return tabunganrepository.save(data_tabungan);
		}else {
			data_tabungan.setSaldo(tabungan.getKredit()-tabungan.getDebet());
			return tabunganrepository.save(data_tabungan);
		}
		
	}

	public List<tabunganModel> findDataBy_nik(String nik) {
		return tabunganrepository.getBy_nik(nik);
	}

	public tabunganModel updateTabungan(tabunganModel data) {
		tabunganModel tamData=data;
		tabunganModel getSl=tabunganrepository.findOne(tamData.getId());
		getSl.setNama(tamData.getNama());
		getSl.setSaldo(getSl.getSaldo()+(tamData.getKredit()-tamData.getDebet()));
		getSl.setKredit(tamData.getKredit());
		getSl.setDebet(tamData.getDebet());
		int saldoAwal=getSl.getSaldo();
		
		List<tabunganModel> dataTabungan=tabunganrepository.getBy_nik(tamData.getNik());
		for(tabunganModel tm:dataTabungan) {
			if(tm.getId()>tamData.getId()) {
				tm.setSaldo(saldoAwal+(tm.getKredit()-tm.getDebet()));
				tabunganrepository.save(tm);
				saldoAwal=tm.getSaldo();
			}
		}
		return null;
	}

	public void deleteTabungan(Long id) {
		tabunganModel Nik=tabunganrepository.findOne(id);
		List<tabunganModel> dataNik=tabunganrepository.getBy_nik(Nik.getNik());
		for (tabunganModel data : dataNik) {
			if(data.getId() > id) {
				tabunganModel tabung=tabunganrepository.findOne(data.getId());
				tabung.setSaldo(tabung.getSaldo()-Nik.getKredit()+Nik.getDebet());
				tabunganrepository.save(tabung);					
			}
		}
		tabunganrepository.delete(id);
		
	}
	
	
	
}
