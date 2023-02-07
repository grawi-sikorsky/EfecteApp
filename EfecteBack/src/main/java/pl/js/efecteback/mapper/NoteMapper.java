package pl.js.efecteback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.js.efecteback.dto.CreateModifyNoteDTO;
import pl.js.efecteback.dto.NoteDTO;
import pl.js.efecteback.model.NoteModel;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    NoteDTO noteToNoteDTO(NoteModel note);

    CreateModifyNoteDTO noteToCreateModifyNoteDTO(NoteModel note);

    NoteModel noteDtoToEntity(NoteDTO noteDTO);

    NoteModel createModifyNoteDtoToEntity(CreateModifyNoteDTO createModifyNoteDTO);

}
