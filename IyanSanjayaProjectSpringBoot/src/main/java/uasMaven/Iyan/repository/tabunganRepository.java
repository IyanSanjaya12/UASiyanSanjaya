package uasMaven.Iyan.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uasMaven.Iyan.model.tabunganModel;

public interface tabunganRepository extends JpaRepository<tabunganModel, Long> {
	@Query(value="Select * from tabungan_tbl where nik=:nik order by id limit 1",nativeQuery=true)
	tabunganModel getSaldoByNik(@Param("nik") String nik);
	
	@Query(value="Select * from tabungan_tbl where nik=:nik",nativeQuery=true)
	List<tabunganModel> getBy_nik(@Param("nik") String nik);
}
