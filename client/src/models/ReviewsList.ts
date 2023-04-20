export type ReviewsList = {
    reviews:    Reviews;
    userReview: null;
}

export type Reviews = {
    content:          Review[];
    pageable:         Pageable;
    totalPages:       number;
    totalElements:    number;
    last:             boolean;
    size:             number;
    number:           number;
    sort:             Sort;
    numberOfElements: number;
    first:            boolean;
    empty:            boolean;
}

export type Review = {
    id:           number;
    score:        number;
    review:       string;
    date:         Date;
    profile:      Profile;
    experienceId: number;
}

export type Profile = {
    id:       number;
    name:     string;
    lastname: string;
}

export type Pageable = {
    sort:       Sort;
    offset:     number;
    pageNumber: number;
    pageSize:   number;
    paged:      boolean;
    unpaged:    boolean;
}

export type Sort = {
    empty:    boolean;
    sorted:   boolean;
    unsorted: boolean;
}